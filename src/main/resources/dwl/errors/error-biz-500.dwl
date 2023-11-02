%dw 2.0
import * from dw::core::Strings
output application/json

var defaultErrorPayload = {
	"description": substringAfter(error.description, "failed:")
}
var payloadCopy = error.errorMessage.payload default defaultErrorPayload
---
{
  "type": p('error.response.code'),
  "title": p('http.response.bad.request.title'),
  "detail": (if (not isEmpty(payloadCopy.description)) payloadCopy.description else "") 
  ++ " " ++
 (if (not isEmpty(payloadCopy.additionalDetails)) payloadCopy.additionalDetails else ""),

  "time": now(),
  "traceId": correlationId
}
