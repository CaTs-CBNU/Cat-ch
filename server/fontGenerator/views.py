from django.shortcuts import render
from django.http import JsonResponse
from rest_framework.viewsets import ViewSet
from trdg.generators import GeneratorFromStrings
import json
from cat_ch.settings import BASE_DIR
import os

class FontGeneratorViewSet(ViewSet):
    def create(self, request):
        try:
            print(request.body)
            request_data = json.loads(request.body)
            text, font = request_data['text'], request_data['font']
            self.font_generator(text, font)
            return JsonResponse({'status': 'success'})
        except Exception as e:
            print("ERROR:", e)
            return JsonResponse({"ERROR": str(e)})

    def font_generator(self, text, font):
        font_path = os.path.join(BASE_DIR, "fontGenerator/fonts/ko/", font + '.ttf')
        train_img_path = os.path.join(BASE_DIR, "fontGenerator/train_images/", font)
        print("Font path:", font_path)

        # 폰트 파일이 존재하는지 확인
        if not os.path.isfile(font_path):
            raise FileNotFoundError(f"Font file not found: {font_path}")

        # 생성된 이미지를 저장할 디렉토리가 존재하지 않으면 생성
        os.makedirs(train_img_path, exist_ok=True)

        for index, char in enumerate(text):
            generator = GeneratorFromStrings(
                strings=[char],
                fonts=[font_path]
            )
            try:
                generated_image, _ = next(generator)
                if generated_image is None:
                    raise ValueError(f"Generated image is None for character: {char}")

                output_file_name = f"{char}_test_font_img_{index}.png"
                output_file_path = os.path.join(train_img_path, output_file_name)
                generated_image.save(output_file_path)
                print(f"Image saved: {output_file_path}")
            except StopIteration:
                print(f"Failed to generate image for character: {char}")
            except Exception as e:
                print(f"Error generating image for character '{char}': {e}")
