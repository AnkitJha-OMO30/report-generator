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
        "field1": "val1",
        "field2": {
            "field21": "val21",
            "field22": "val22",
            "field23": [
                {
                    "field231": "val3231[0]"
                }
            ]
        },
        "field3": [
            {
                "field31": "val31[0]",
                "field32": "val32[0]"
            },
            {
                "field31": "val331[1]",
                "field32": "val332[1]"
            }
        ]
    },
    {
        "field1": "val22",
        "field2": {
            "field21": "val21",
            "field22": "val22",
            "field23": [
                {
                    "field231": "val3231[0]"
                }
            ]
        },
        "field3": [
            {
                "field31": "val31[0]",
                "field32": "val32[0]"
            },
            {
                "field31": "val331[1]",
                "field32": "val332[1]"
            }
        ]
    }
]
```

CSV Generated
```
field1,field2.field21,field2.field22,"field2.field23[0].field231",field3[0].field31,field3[0].field32,field3[1].field31,field3[1].field32
val1,val21,val22,val3231[0],val31[0],val32[0],val331[1],val332[1]
val22,val21,val22,val3231[0],val31[0],val32[0],val331[1],val332[1]
```

### CSV to JSON 

This functionality Converts CSV File to JSON File. 

To utilise the API, call the below API

```
/getJsonFromCsv
```

This API converts CSV to JSON, JSON to Java POJO. For more info, check out the CsvToJsonReportGenerator.java


Above APIs are still in development, so any feedback is welcome!!

