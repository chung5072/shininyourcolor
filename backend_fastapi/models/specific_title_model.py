import os
os.environ["KERAS_BACKEND"] = "tensorflow"

import json
from keras.models import load_model

# 함수 테스트 용
# from transformers import AutoTokenizer

'''
필요한 파일 불러오기
'''
# 메타데이터를 JSON 파일을 불러옴
with open('./models/metadatas/spec_title_model_metadata.json', 'r') as f:
    metadata = json.load(f)

# 저장된 모델 불러오기
loaded_model = load_model('./models/saved_model_files/spec_title_model.keras')

def categorize_specific_title(tokenizer, content):
    '''
    데이터 전처리
    '''
    # 저장된 `max_length` 값을 가져옵니다.
    max_length = metadata['max_length']

    tokenized_content = tokenizer(
        content,
        return_tensors="tf",
        max_length = max_length,
        padding="max_length",
        truncation=True,
        add_special_tokens=True
    )

    # 필요한 입력 텐서를 추출합니다.
    input_ids = tokenized_content['input_ids']

    '''
    예측 및 결과
    '''
    # 모델을 사용하여 예측합니다.
    prediction = loaded_model.predict(input_ids)
    print(prediction)

    # 0.5를 기준으로 임계값 설정
    threshold = 0.5

    if (prediction > threshold) == 1:
        result = 'praise'
    else:
        result = 'cheer'

    print(result)

    return result

# 함수 테스트 용
# 사전 훈련된 모델에 대한 토크나이저를 불러옴
# MODEL_NAME = "beomi/KcELECTRA-base"
# tokenizer = AutoTokenizer.from_pretrained(MODEL_NAME)

# print(categorize_specific_title(tokenizer=tokenizer, new_str="이번에 인사이드아웃2봤어? 진짜 너무 명작이더라 ㅠㅠㅠ"))