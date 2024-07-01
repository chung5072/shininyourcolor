import json
import numpy as np

import torch
from transformers import ElectraForSequenceClassification, Trainer

# 함수 테스트 용
# from transformers import AutoTokenizer

'''
필요한 파일 및 변수 불러오기
'''
# 메타데이터를 JSON 파일을 불러옴
with open('./models/metadatas/part_kc_model_metadata.json', 'r') as f:
    metadata = json.load(f)

# 저장된 모델 불러오기
MODEL = ElectraForSequenceClassification.from_pretrained("./models/saved_model_files/part_kc_model") 

# PyTorch 라이브러리를 사용하여 데이터셋 클래스를 정의
# PyTorch의 DataLoader와 함께 사용되어 모델 학습에 필요한 배치를 생성하는 데 사용
class WordDataset(torch.utils.data.Dataset):
    def __init__(self, encodings): # 클래스의 인스턴스를 초기화
        self.encodings = encodings # encodings는 텍스트 데이터의 인코딩(토큰화, 인덱싱 등)
        
    def __getitem__(self, idx): # 이 메소드는 인덱스 idx에 해당하는 데이터와 레이블을 반환
        # torch.tensor를 사용하여 데이터와 레이블을 PyTorch 텐서로 변환
        item = {key: val[idx] for key, val in self.encodings.items()}
        return item
    
    def __len__(self): # 이 메소드는 데이터셋의 총 데이터 개수를 반환
        return len(self.encodings['input_ids'])

def categorize_part_withkc(tokenizer, content):
    '''
    데이터 전처리
    '''
    # 저장된 `max_length` 값을 가져옵니다.
    max_length = metadata['max_length']

    tokenized_content = tokenizer(
        content,
        return_tensors="pt",
        max_length = min(max_length, 512),
        padding="max_length",
        truncation=True,
        add_special_tokens=True
    )

    content_dataset = WordDataset(tokenized_content)

    trainer = Trainer(model = MODEL)

    MODEL.eval() # 모델을 평가 모드로 전환

    predictions = trainer.predict(content_dataset)

    print("각 레이블에 속할 확률:", predictions)

    # 가장 높은 확률 값을 가진 인덱스를 찾습니다.
    predicted_class_index = np.argmax(predictions[0])
    
    if predicted_class_index == 0:
        return "none"
    elif predicted_class_index  == 1:
        return "negative"
    else:
        return "positive"

# 함수 테스트 용
# 사전 훈련된 모델에 대한 토크나이저를 불러옴
# MODEL_NAME = "beomi/KcELECTRA-base"
# tokenizer = AutoTokenizer.from_pretrained(MODEL_NAME)

# print(categorize_part_withkc(tokenizer=tokenizer, new_str="대분류 테스트"))