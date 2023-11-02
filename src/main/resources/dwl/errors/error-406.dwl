%dw 2.0
output application/json
---
{
  "type": p('error.response.code'),
  "title": p('http.response.not.acceptable.title'),
  "detail": error.description,
  "time": now(),
  "traceId": correlationId
}
