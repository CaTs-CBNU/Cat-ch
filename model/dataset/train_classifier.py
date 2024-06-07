import torch
import torch.nn as nn
import torch.optim as optim
from torch.utils.data import Dataset, DataLoader
from torchvision import models, transforms
from PIL import Image
import os
from torchvision.models import ResNet18_Weights
from tqdm import tqdm  # tqdm 라이브러리 import

# 이미지 파일이 있는 디렉토리
current_dir = os.path.dirname(__file__)
img_dir = os.path.join(current_dir, "train_images")
# img_dir 하위의 모든 디렉토리 가져오기
subdirectories = [os.path.join(img_dir, d) for d in os.listdir(img_dir) if os.path.isdir(os.path.join(img_dir, d))]
os.environ["CUDA_VISIBLE_DEVICES"]= "0,1"  # Set the GPUs 2 and 3 to use

# 각 하위 디렉토리에 있는 이미지 파일의 경로와 라벨 수집
data = []
for subdir in subdirectories:
    label = os.path.basename(subdir)
    image_files = [os.path.join(subdir, f) for f in os.listdir(subdir) if f.endswith(".png")]
    for image_file in image_files:
        data.append((image_file, label))

# 사용할 디바이스 설정
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
resnet = models.resnet18(weights=ResNet18_Weights.DEFAULT)

print('Count of using GPUs:', torch.cuda.device_count())   #출력결과: 2 (2, 3 두개 사용하므로)
print('Current cuda device:', torch.cuda.current_device())  # 출력결과: 2 (2, 3 중 앞의 GPU #2 의미)

# change the output layer to 10 classes
num_classes = 41
num_ftrs = resnet.fc.in_features
resnet.fc = nn.Linear(num_ftrs, num_classes)

# GPU 2개를 사용할 수 있도록 모델을 병렬 처리
if torch.cuda.device_count() > 1:
    print("Using", torch.cuda.device_count(), "GPUs!")
    resnet = nn.DataParallel(resnet).to(device)

# 데이터셋 클래스 정의
class FontDataset(Dataset):
    def __init__(self, data, transform=None):
        self.data = data
        self.transform = transform
    
    def __len__(self):
        return len(self.data)
    
    def __getitem__(self, idx):
        image_path, label = self.data[idx]
        image = Image.open(image_path).convert("RGB")
        if self.transform:
            image = self.transform(image)
        return image, int(label)
    
# 이미지 변환 및 데이터로더 생성
transform = transforms.Compose([
    transforms.Resize((224, 224)),
    transforms.ToTensor(),
    transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
])
dataset = FontDataset(data, transform=transform)
train_loader = DataLoader(dataset, shuffle=True)

# 모델 및 손실 함수, 최적화기 생성
model = resnet
criterion = nn.CrossEntropyLoss()
optimizer = optim.Adam(model.parameters(), lr=0.001)

# 모델 학습
num_epochs = 10
for epoch in range(num_epochs):
    model.train()
    running_loss = 0.0
    with tqdm(total=len(train_loader), desc=f"Epoch {epoch+1}/{num_epochs}", unit="batch") as pbar:
        for images, labels in train_loader:
            images, labels = images.to(device), labels.to(device)
            optimizer.zero_grad()
            outputs = model(images)
            loss = criterion(outputs, labels)
            loss.backward()
            optimizer.step()
            running_loss += loss.item() * images.size(0)
            pbar.update(1)
    epoch_loss = running_loss / len(dataset)
    print(f"Epoch [{epoch+1}/{num_epochs}], Loss: {epoch_loss:.4f}")

# 학습된 모델 저장
torch.save(model.state_dict(), "font_classifier.pth")
