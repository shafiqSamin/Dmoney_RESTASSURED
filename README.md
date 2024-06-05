# Automated Testing for Dmoney API

This repository hosts automated test scripts for testing the Dmoney API using Rest Assured. These scripts automate various tasks specified in the project requirements.

## Setting Up and Running Tests

Follow these steps to configure and execute the automated tests:

### Usage

1. Navigate to the project directory.

2. Install necessary dependencies by executing:

    ```
    gradle clean install
    ```

### Configuration Update

- Edit the `config.properties` file:
  - Set the `baseUrl` to the Dmoney API's URL.
  - Update `partnerKey` and `token` for authentication.

### Data Preparation

1. Generate a JSON file with required data for customer and agent creation, following the task description.

2. Save this JSON file in the project directory.

### Test Execution

1. Run the automated test suite against the Dmoney API.
2. Ensure all tests execute successfully:

    ```
    gradle clean test
    ```

## Covered Scenarios

The automated tests address the following scenarios:

- Admin login.
- Creating two new customers and an agent.
- Transferring 2000 tk from the System account to the new agent.
- Depositing 1500 tk to a customer from the agent account.
- Withdrawing 500 tk by the customer to the agent.
- Transferring 500 tk to another customer.
- Making a payment of 100 tk to a merchant (01686606905) by the recipient customer.
- Verifying the balance of the recipient customer.

## Allure Report Generation

After running the tests, use the following commands to generate and view the Allure report:

