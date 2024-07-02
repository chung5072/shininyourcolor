from transformers import AutoTokenizer

from models.part_kc_model import categorize_part_withkc
from models.negative_chapter_model import categorize_negative_chapter
from models.positive_chapter_model import categorize_positive_chapter
from models.specific_title_model import categorize_specific_title
from models.unspecific_title_model import categorize_unspecific_title

'''
필요한 변수 정의
'''
# 전 모델 공통
# 사전 훈련된 모델의 이름을 지정
MODEL_NAME = "beomi/KcELECTRA-base"
# 지정된 모델에 대한 토크나이저를 불러옴
tokenizer = AutoTokenizer.from_pretrained(MODEL_NAME)

def categorize_content(content: str):

    label = categorize_part_withkc(tokenizer, content)

    if label == "none":
        return "none"
    elif label == "negative":
        negative_chapter = categorize_negative_chapter(tokenizer, content)

        if negative_chapter == "compare":
                return "compare"
        else:
            # offensive
            return "offensive"
    else:
        positive_chapter = categorize_positive_chapter(tokenizer, content)

        if positive_chapter == "specific":
            specific_title = categorize_specific_title(tokenizer, content)

            if specific_title == "praise":
                return "specific_praise"
            else:
                # cheer
                return "specific_cheer"
        else:
            unspecific_title = categorize_unspecific_title(tokenizer, content)

            if unspecific_title == "praise":
                return "unspecific_praise"
            else:
                # cheer
                return "unspecific_cheer"