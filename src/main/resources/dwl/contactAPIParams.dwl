%dw 2.0
output application/java
---
{
	headers: 
		{
		'X-Client-Id': p('secure::acct.mgmt.process.api.clientid'),
		'X-Client-Secret': p('secure::acct.mgmt.process.api.clientsecret'),
		
		'Content-Type': "application/json"
		},	
		
	"uriParams": {
		"contactId": vars.contactId
	}
}