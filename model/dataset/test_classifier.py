import torch
import torch.nn as nn
from torchvision import models, transforms
from PIL import Image
import os
from torchvision.models import ResNet18_Weights

# 이미지 파일이 있는 디렉토리
current_dir = os.path.dirname(__file__)
pth_path = os.path.join(current_dir, "font_classifier.pth")
image_path = os.path.join(current_dir, "test_dataset/가_0_img77551.png")
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")

# 모델 정의
model = models.resnet18(weights=None)
num_classes = 41
num_ftrs = model.fc.in_features
model.fc = nn.Linear(num_ftrs, num_classes)

# 학습된 모델의 가중치 불러오기
state_dict = torch.load(pth_path, map_location=device)
model.to(device)
model.eval()  # 모델을 평가 모드로 설정

# 데이터 전처리 함수 정의
def preprocess_image(image_path):
    transform = transforms.Compose([
        transforms.Resize((224, 224)),
        transforms.ToTensor(),
        transforms.Normalize(mean=[0.485, 0.456, 0.406], std=[0.229, 0.224, 0.225])
    ])
    image = Image.open(image_path).convert("RGB")
    image = transform(image)
    return image.unsqueeze(0)  # 배치 차원 추가

# 이미지 전처리 및 모델 입력
image = preprocess_image(image_path)
image = image.to(device)

# 모델에 이미지 입력하여 예측값 얻기
with torch.no_grad():
    outputs = model(image)
    _, predicted = torch.max(outputs, 1)

# 예측값 출력
predicted_class = predicted.item()
print("Predicted Class:", predicted_class)
