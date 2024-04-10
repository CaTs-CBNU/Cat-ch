import os
import random
from trdg.generators import GeneratorFromStrings

# 입력 파일 경로
txt_file_path = "model/dataset/dicts/ks1001.txt"
fonts_dir = "model/dataset/ko"

font_paths = [os.path.join(fonts_dir, file) for file in os.listdir(fonts_dir) if file.endswith(".ttf")]

with open(txt_file_path, 'r', encoding='utf-8') as file:
    content = file.read()
content_list = content.split()

output_dir = "model/dataset/train_datasets"

os.makedirs(output_dir, exist_ok=True)

# 각 폰트명에 대한 디렉터리 생성
font_dirs = [os.path.join(output_dir, os.path.splitext(os.path.basename(font_path))[0]) for font_path in font_paths]
for font_dir in font_dirs:
    os.makedirs(font_dir, exist_ok=True)
    
print(font_dirs)

counter = 1  # 이미지 번호 카운터
for word in content_list:
    # 단어에서 랜덤으로 한 글자 선택
    random_char = random.choice(word)
    # 랜덤 폰트 선택
    random_font_path = random.choice(font_paths)
    # print("랜덤글자: {}".format(random_char))
    font_name = os.path.basename(random_font_path).split('.')[0]
    print(font_name)

    # 이미지 생성
    generator = GeneratorFromStrings(
        strings=[word],
        fonts=[random_font_path]  # 랜덤으로 선택한 폰트 경로 전달
    )
    generated_image, _ = next(generator)  # 튜플에서 이미지만 추출
    
    output_font_dir = font_dirs[font_paths.index(random_font_path)]

    # 파일명에 랜덤 글자, 폰트 번호, 이미지 번호 포함하여 저장
    output_file_name = f"{random_char}_{font_name}_img{counter}.png"
    output_file_path = os.path.join(output_font_dir, output_file_name)
    generated_image.save(output_file_path)
    counter += 1  # 이미지 번호 증가

