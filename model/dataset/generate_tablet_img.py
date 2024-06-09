import os
import random
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import font_manager as fm
from matplotlib.patheffects import withStroke

def draw_grid_image(grid_size, image_size, text):
    """
        지정된 격자 크기와 이미지 크기에 따라 격자 이미지를 생성

        Parameters:
            grid_size (int): 생성할 격자의 크기 (픽셀)
            image_size (int): 생성할 이미지의 크기 (픽셀)
            text (str): 격자에 배치할 텍스트

        Returns:
            None
    """
    # 빈 이미지 생성 (흰색, RGB)
    image = np.ones((image_size, image_size, 3))  

    # 그림과 축 생성
    fig, ax = plt.subplots(figsize=(image_size / 100, image_size / 100))

    # 격자 라인 그리기
    for i in range(0, image_size, grid_size):
        ax.axhline(y=i, color='black', linewidth=0.5)  # 가로 격자 라인 그리기
        ax.axvline(x=i, color='black', linewidth=0.5)  # 세로 격자 라인 그리기

    # 폰트 설정을 위해 외부 폰트 파일 로드
    prop = fm.FontProperties(fname="./fonts/ko/1.ttf") 

    # 텍스트를 한글자씩 리스트로 변환
    text_chars = list(text)

    # 텍스트를 격자에 추가하기
    text_index = 0
    for row in range(0, image_size, grid_size * 2):  # 두 줄마다 글자가 들어가도록 설정
        i = image_size - grid_size - row  # 맨 위에서부터 시작하도록 수정
        for j in range(0, image_size, grid_size):
            if text_index >= len(text_chars):
                break  # 텍스트의 끝에 도달하면 중지
            center_x = j + grid_size // 2  # 가운데 x 좌표 계산
            center_y = i + grid_size // 2  # 가운데 y 좌표 계산
            char = text_chars[text_index]  # 텍스트에서 한 글자 가져오기
            txt = ax.text(center_x, center_y, char, ha='center', va='center', fontproperties=prop, fontsize=grid_size // 2, color='white')
            txt.set_path_effects([withStroke(linewidth=1, foreground='black')])
            text_index += 1

    # x축, y축 limit 설정
    ax.set_xlim(0, image_size)
    ax.set_ylim(0, image_size)

    ax.set_xticks([])  # x 눈금 제거
    ax.set_yticks([])  # y 눈금 제거
    ax.set_xticklabels([])  # x 라벨 제거
    ax.set_yticklabels([])  # y 라벨 제거
    
    ax.imshow(image)
    # 그림 저장
    plt.savefig('output2.png', bbox_inches='tight', dpi=100)
    # Display the grid image
    plt.tight_layout()
    plt.show()

def main():
    grid_size = 256  # 격자 크기 (픽셀)
    image_size = 2048  # 이미지 크기 (픽셀)
    text = "안녕하세요?. 테스트입니다! CAT-CH 파이팅.!"  # 격자에 배치할 텍스트

    # 격자 이미지 생성
    draw_grid_image(grid_size, image_size, text)

if __name__ == "__main__":
    main()