import uvicorn
from fastapi import FastAPI, Request
from fastapi.responses import JSONResponse
from fastapi.exceptions import RequestValidationError
from pydantic import BaseModel, Field
from classify import categorize_content

# Spring Boot -> FastAPI
class TalkContent(BaseModel):
    content: str = Field(...)

# Spring Boot <- FastAPI
class TalkCategory(BaseModel):
    category: str

app = FastAPI()

@app.exception_handler(RequestValidationError)
async def validation_exception_handler(request: Request, exc: RequestValidationError):
    body = await request.body()
    body_text = body.decode('utf-8')  # 바이트를 문자열로 디코드
    # 유효성 검사 오류를 로그에 기록
    print(f"Validation error for request {request.url}: {exc} with body {body_text}")
    # 클라이언트에게 오류 응답 반환
    return JSONResponse(
        status_code=422,
        content={"detail": exc.errors()},
    )

@app.post("/categorize")
def categorize(talk_content: TalkContent):
    print(talk_content.content)
    result = categorize_content(talk_content.content)
    print("결과:", result)
    # Pydantic 모델 인스턴스 생성 및 반환
    return TalkCategory(category = result)