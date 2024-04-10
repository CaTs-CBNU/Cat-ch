import os
import random
from trdg.generators import GeneratorFromStrings
import csv

current_dir = os.path.dirname(__file__)
fonts_dir = os.path.join(current_dir, "fonts/ko")
txt_file_path = os.path.join(current_dir, "dicts/ks1001.txt")
output_dir = os.path.join(current_dir, "train_images")
csv_file_path = os.path.join(output_dir, "image_font_mapping.csv")

font_paths = [os.path.join(fonts_dir, file) for file in os.listdir(fonts_dir) if file.endswith(".ttf")]

with open(txt_file_path, 'r', encoding='utf-8') as file:
    content = file.read()
content_list = content.split()

os.makedirs(output_dir, exist_ok=True)

# 각 폰트명에 대한 디렉터리 생성
font_dirs = [os.path.join(output_dir, os.path.splitext(os.path.basename(font_path))[0]) for font_path in font_paths]
for font_dir in font_dirs:
    os.makedirs(font_dir, exist_ok=True)
    
print(font_dirs)

# CSV 파일에 저장할 데이터 준비
data = [("index", "imageName", "fontName", "text")]
counter = 1  # 이미지 번호 카운터

for font_path, font_dir in zip(font_paths, font_dirs):
    font_name = os.path.basename(font_path).split('.')[0]
    print("폰트:", font_name)

    for word in content_list:
        for char in word:
            # 이미지 생성
            generator = GeneratorFromStrings(
                strings=[char],
                fonts=[font_path]  # 현재 폰트 경로 전달
            )
            generated_image, _ = next(generator)  # 튜플에서 이미지만 추출
            
            # 파일명에 랜덤 글자, 폰트 번호, 이미지 번호 포함하여 저장
            output_file_name = f"{char}_{font_name}_img{counter}.png"
            output_file_path = os.path.join(font_dir, output_file_name)
            generated_image.save(output_file_path)
            data.append((counter, output_file_name, font_name, char))

            counter += 1  # 이미지 번호 증가

# CSV 파일 작성
with open(csv_file_path, 'w', newline='', encoding='utf-8') as file:
    writer = csv.writer(file)
    writer.writerows(data)