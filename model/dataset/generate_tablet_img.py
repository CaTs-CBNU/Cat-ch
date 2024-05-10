import os
import random
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import font_manager as fm
from matplotlib.patheffects import withStroke



def draw_grid_image(grid_size, image_size):
    """
        지정된 격자 크기와 이미지 크기에 따라 격자 이미지를 생성

        Parameters:
            grid_size (int): 생성할 격자의 크기 (픽셀)
            image_size (int): 생성할 이미지의 크기 (픽셀)

        Returns:
            None
    """
    # 빈 이미지 생성 (흰색, RGB)
    image = np.ones((image_size, image_size, 3))  

    # 그림과 축 생성
    fig, ax = plt.subplots(figsize=(image_size / 100, image_size / 100))

    # 격자 라인 그리기
    # 격자 크기만큼 반복하면서 가로선과 세로선을 그림
    for i in range(0, image_size, grid_size):
        ax.axhline(y=i, color='black', linewidth=0.5)  # 가로 격자 라인 그리기
        ax.axvline(x=i, color='black', linewidth=0.5)  # 세로 격자 라인 그리기

    # for i in range(0, image_size, (grid_size // 2)):
    #     ax.axhline(y=i, color='gray', linestyle='--', linewidth=0.5)  # Horizontal dashed lines
    #     ax.axvline(x=i, color='black', linestyle='--', linewidth=0.5)  # Vertical dashed lines
    #     # ax.axvline(x=i, ymin=48/image_size, ymax=64/image_size, color='gray', linestyle='--', linewidth=0.5)  # Vertical dashed lines
        
    # 폰트 설정을 위해 외부 폰트 파일 로드
    prop = fm.FontProperties(fname="./fonts/ko/1.ttf") 

        
    # 홀수 행에만 글자 추가하기
    for i in range(0, image_size, grid_size):
        # 행 번호가 짝수인 경우에는 건너뜀
        if ((i // grid_size) % 2 == 0):
            continue
        for j in range(0, image_size, grid_size):
            center_x = j + grid_size // 2 # 가운데 x 좌표 계산
            center_y = i + grid_size // 2 # 가운데 y 좌표 계산
            # ax.text(center_x, center_y, '안', ha='center', va='center', fontproperties = prop, fontsize = grid_size // 8)
            txt = ax.text(center_x, center_y, '안', ha='center', va='center', fontproperties=prop, fontsize=grid_size // 2, color='white')
            txt.set_path_effects([withStroke(linewidth=1, foreground='black')])

    # x축, y축 limit 설정
    ax.set_xlim(0, image_size)
    ax.set_ylim(0, image_size)

    ax.set_xticks([]) # x 눈금 제거
    ax.set_yticks([]) # y 눈금 제거
    ax.set_xticklabels([]) # x 라벨 제거
    ax.set_yticklabels([]) # y 라벨 제거
    
    ax.imshow(image)
    # 그림 저장
    plt.savefig('output2.png', bbox_inches='tight', dpi=100)
    # Display the grid image
    plt.tight_layout()
    plt.show()

def main():
    grid_size = 256  # 격자 크기 (픽셀)
    image_size = 2048  # 이미지 크기 (픽셀)

    # 격자 이미지 생성
    draw_grid_image(grid_size, image_size)

if __name__ == "__main__":
    main()
