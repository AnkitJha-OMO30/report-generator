# report-generator
This repository handles the json to PDF generation

This Utilises OpenHTMLToPdf and FreeMarker Dependencies to convert JSON data initially to HTML code and then converts the HTML Page to PDF. 

To test the API, you can utilise the JSON in the following format
```
{
"user":"User123",
"account":"3323472929",
"balance": 12345,
"pdfName":"<PDF Name for your File>",
"template":"1.ftp",
"transactions": [
        {
		"acc":"1234567890",
		"amount": 123,
		"transactionType":"Credit"
        },
        {	
		"acc":"2132354445",
		"amount": 334,
        "transactionType":"Debit"
        },
        {
            "acc":"646464",
            "amount":660,
            "transactionType":"Credit"
        },
        {
            "acc":"646463",
            "amount":662,
            "transactionType":"Debit"
        },
        {
            "acc":"5346464",
            "amount":160,
            "transactionType":"Credit"
        },
        {
            "acc":"6464621",
            "amount":611,
            "transactionType":"Debit"
        }
    ],
    "loans":[
        {
            "lender":"XYZ FinTech Solutions",
            "duration":"40 months",
            "amount":5000000
        },
        {
            "lender":"420 solutions",
            "duration":"20 months",
            "amount":7755757
        },
        {
            "lender":"Fakirchand and Lakirchand Trust University FinTech Group",
            "duration":"49 months",
            "amount":776655443
        }
    ]
}
```
Here, Adding loans array can be optional and you need not necessarily add them. 
To call the report generation, use the /getPDF API, and to get the HTML part, utilise the /getReport API. 

It's still in development, so any feedback is welcome!!
