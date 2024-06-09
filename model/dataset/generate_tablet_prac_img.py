import os
import matplotlib.pyplot as plt
import numpy as np
from matplotlib import font_manager as fm
from matplotlib.patheffects import withStroke

def draw_grid_image(chars, output_filename, font_path):
    """
        한글 자음체계를 격자에 표시하는 이미지 생성

        Parameters:
            chars (list of str): 격자에 배치할 한글 자음 리스트
            output_filename (str): 생성할 이미지 파일 이름
            font_path (str): 폰트 파일 경로

        Returns:
            None
    """
    # 한글 자음 개수
    num_chars = len(chars)
    
    # 격자 크기 및 이미지 크기 설정
    grid_size = 100  # 각 격자의 크기 (픽셀)
    grid_cols = int(np.ceil(np.sqrt(num_chars)))  # 격자의 열 개수 (제곱근을 올림)
    grid_rows = int(np.ceil(num_chars / grid_cols))  # 격자의 행 개수 (총 문자수를 열 개수로 나눔)
    image_width = grid_size * grid_cols  # 이미지 너비 (픽셀)
    image_height = grid_size * grid_rows  # 이미지 높이 (픽셀)

    # 빈 이미지 생성 (흰색, RGB)
    image = np.ones((image_height, image_width, 3))  

    # 그림과 축 생성
    fig, ax = plt.subplots(figsize=(image_width / 100, image_height / 100))

    # 격자 라인 그리기
    for i in range(0, image_height + grid_size, grid_size):
        ax.axhline(y=i, color='black', linewidth=0.5)  # 가로 격자 라인 그리기
    for i in range(0, image_width + grid_size, grid_size):
        ax.axvline(x=i, color='black', linewidth=0.5)  # 세로 격자 라인 그리기

    # 폰트 설정을 위해 외부 폰트 파일 로드
    prop = fm.FontProperties(fname=font_path) 

    # 텍스트를 격자에 추가하기
    text_index = 0
    for row in range(grid_rows):
        for col in range(grid_cols):
            if text_index >= num_chars:
                break  # 텍스트의 끝에 도달하면 중지
            center_x = col * grid_size + grid_size // 2  # 가운데 x 좌표 계산
            center_y = (grid_rows - row - 1) * grid_size + grid_size // 2  # 가운데 y 좌표 계산, 위쪽부터 시작하도록 수정
            char = chars[text_index]  # 한글 자음 가져오기
            txt = ax.text(center_x, center_y, char, ha='center', va='center', fontproperties=prop, fontsize=grid_size // 2, color='white')
            txt.set_path_effects([withStroke(linewidth=1, foreground='black')])
            text_index += 1

    # x축, y축 limit 설정
    ax.set_xlim(0, image_width)
    ax.set_ylim(0, image_height)

    ax.set_xticks([])  # x 눈금 제거
    ax.set_yticks([])  # y 눈금 제거
    ax.set_xticklabels([])  # x 라벨 제거
    ax.set_yticklabels([])  # y 라벨 제거
    
    ax.imshow(image)
    # 그림 저장
    plt.savefig(output_filename, bbox_inches='tight', dpi=100)
    # Display the grid image
    plt.tight_layout()
    plt.show()

def main():
    # 한글 자음 리스트
    hangul_initials = [
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ',
        'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    ]
    
    # 한글 모음 리스트
    hangul_vowels = [
        'ㅏ', 'ㅐ', 'ㅑ', 'ㅒ', 'ㅓ', 'ㅔ', 'ㅕ', 'ㅖ', 'ㅗ',
        'ㅘ', 'ㅙ', 'ㅚ', 'ㅛ', 'ㅜ', 'ㅝ', 'ㅞ', 'ㅟ', 'ㅠ',
        'ㅡ', 'ㅢ', 'ㅣ'
    ]
    
    # 폰트 파일 경로
    font_name = '3'
    font_path = f"./fonts/ko/{font_name}.ttf"
    
    # 자음 격자 이미지 생성
    draw_grid_image(hangul_initials, f'hangul_initials_{font_name}.png', font_path)
    
    # 모음 격자 이미지 생성
    draw_grid_image(hangul_vowels, f'hangul_vowels_{font_name}.png', font_path)

if __name__ == "__main__":
    main()