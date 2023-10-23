# javaDataGen

## About
It's a java package that generates a fake purchase JSON and push it to a Kinesis Data Stream.

It defines a limited (configurable) list Buyers, a limited (configurable) list of Sellers, a limited (configurable) list of Departments and a limited (configurable) list of Products.

It keeps generating fake purchases from the limited lists so there can be orders from the same product or derpartment or sellers or buyer in several JSONs. 
This way you can aggregate, group, query, process, etc... the data simulating your business logic.

It can generate in single purchases or in batches (size is configurable) according to the structure:

- the Seller
- the Buyer
- the Order 
- the Payment

```
{
  "Seller": {
    "CompanyName": "Bergstrom Inc",
    "Email": "ashlea.bartell@bergstrominc.com",
    "Address": "648 Shelby View, 77785",
    "ZipCode": "36041-0295",
    "State": "Oregon",
    "City": "Andersonhaven",
    "TelephoneNumber": "(269) 660-8838"
  },
  "Buyer": {
    "Email": "wyatt.nienow@.@yahoo.com",
    "Address": "03361 Bobby Glen, 4887",
    "FirstName": "Wyatt",
    "ZipCode": "70074",
    "State": "New Mexico",
    "LastName": "Nienow",
    "City": "Turcotteburgh",
    "TelephoneNumber": "404.931.8539"
  },
  "Order": [
    {
      "Department": "Garden & Shoes",
      "UnitPrice": 68.17,
      "Product": "Small Copper Shirt",
      "Quantity": 7,
      "SubTotal": 477.19
    },
    {
      "Department": "Books",
      "UnitPrice": 41.48,
      "Product": "Sleek Wooden Table",
      "Quantity": 5,
      "SubTotal": 207.39999999999998
    }
  ],
  "PaymentMethod": "CASH_CHECK",
  "PayableAmount": 684.5899999999999
}
```

## Usage
Clone or download this repository.

The current code authenticates to AWS by reading from the credentials file located at * (~/.aws/credentials).

If you want to integrate with your projects, import javaDataGen and call directly the producer of your choice. See example code :
```
public class Main {
    public static void main(String[] args) {
        try {
            ProducerKinesis pk = new ProducerKinesis();
            pk.produceMessageSingle();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

You can also integrate only for generating the fake purchase data.
```
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        try {
            PurchaseData pd = new PurchaseData();
            // returns a single JSON message
            String message = pd.generatePurchaseJSON();
            // returns a batch of 1000 JSON messages
            ArrayList<String> messageBatch = pd.generatePurchaseBatchJSON(1000);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
```

### PARAMS PurchaseData
- LIST_SIZE_SELLER: defines the number of unique Sellers javaDataGen will use to create purchase data
- LIST_SIZE_BUYER: defines the number of unique Buyers javaDataGen will use to create purchase data
- LIST_SIZE_PRODUCT_DEPARTMENT:  defines the number of unique Product Department javaDataGen will use to create purchase data
- LIST_SIZE_PRODUCT:  defines the number of unique Products javaDataGen will use to create purchase data

### PARAMS ProducerKinesis
- KDS_STREAM_NAME: is the name of the stream javaDataGen will push data to (Kinesis Data Stream)

## Support
I am happy for you to reach out anytime. Send emails to: gturrini@amazon.co.uk

## Roadmap
I am planning to add connector so that javaDataGen will produce data for additional message brokers/event stream (e.g.: ActiveMQ, MSK, SNS, etc...).

Feel free to reach out with suggestions for prioritization or new ideas.

## Security

See [CONTRIBUTING](./CONTRIBUTING.md#security-issue-notifications) for more information.

## Code of Conduct

See [CODE OF CONDUCT](./CODE_OF_CONDUCT.md) for more information.

## License

This library is licensed under the MIT-0 License. See the [LICENSE](./LICENSE) file.