# report-generator
This repository handles various Report Generation

### Json to PDF Report Generation

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

### JSON To CSV

This Functionality Generates CSV Report after getting data in JSON. It makes use of json-flattener, commons-io and json dependencies.

To Call it, make use of below API

```
/getCSV
```

#### Sample JSON to CSV Generation

JSON

```
[
    {
        "field31":"val31",
        "field32":{
            "field321":"val321",
            "field322":"val322",
            "field323":[
                {
                    "field3231":"val3231"
                }
            ]
        },
        "field33":[
            {
                "field331":"val331",
                "field332":"val332"
            },
            {
                "field331":"val331",
                "field332":"val332"
            }
        ]
    },
    {
        "field31":"uniqueVal31",
        "field32":{
            "field321":"val321",
            "field322":"val322",
            "field323":[
                {
                    "field3231":"val3231"
                }
            ]
        },
        "field33":[
            {
                "field331":"val331",
                "field332":"val332"
            },
            {
                "field331":"val331",
                "field332":"val332"
            }
        ],
        "field34":"val34"
    }
]

```

CSV Generated
```
field33[0].field331,field32.field321,field34,field33[1].field332,field31,field32.field322,field33[1].field331,field32.field323[0].field3231,field33[0].field332
2val331,val321,,val332,val31,val322,val331,val3231,val332
3val331,val321,val34,val332,uniqueVal31,val322,val331,val3231,val332
```

### CSV to JSON 

This functionality Converts CSV File to JSON File. 

To utilise the API, call the below API

```
/getJsonFromCsv
```




Above APIs are still in development, so any feedback is welcome!!

