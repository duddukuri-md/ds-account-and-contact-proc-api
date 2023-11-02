%dw 2.0
output application/json
var payloadCopy = error.errorMessage.payload default payload
---
{
  "type": p('error.response.code'),
  "title": p('http.response.not.found.title'),
  "detail": (if (not isEmpty(payloadCopy.description)) payloadCopy.description else "") 
  ++ " " ++
 (if (not isEmpty(payloadCopy.additionalDetails)) payloadCopy.additionalDetails else ""),
  "time": now(),
  "traceId": vars.daHeaders.traceId
}