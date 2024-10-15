# Clutch Funds 

## Table of contents 

1. [overview](#overview)
2. [Product Spec](#product-spec)
3. [Navigation](#navigation)
4. [Wireframes](#wireframes)

## Overview 
### Description
Clutch Funds is a streamlined expense-tracking app designed to help users take control of their finances effortlessly. With intuitive features, it allows users to monitor spending, set budgeting goals, and make informed decisions to manage their money better. Ideal for anyone seeking a simple, effective way to stay on top of their financial health.

### App Evaluation
**Category:** Finance / Personal Budgeting

**Story:** Clutch Funds is designed to simplify personal finance management by allowing users to track their expenses and monitor their spending habits. The app helps users stay on top of their budgets, providing insights to help them make smarter financial decisions. With a sleek interface and practical features, **Clutch Funds** empowers users to achieve their financial goals.

**Market:** The app targets young professionals, students, and anyone looking for an easy and intuitive way to manage their personal finances. It offers features tailored to individual needs, making it accessible for a broad audience while focusing on ease of use.

**Habit:** Clutch Funds can be used daily for consistent expense tracking or occasionally to review spending and make adjustments to personal budgets. The frequency depends on how closely the user wants to manage their finances.

**Scope:** The initial version of Clutch Funds focuses on expense tracking and budgeting. Future updates could include integration with financial accounts for automatic expense categorization, savings goal features, and potential partnerships with digital banking or payment platforms. This could further expand its capabilities and convenience for users.

## Product Spec
### 1. User Stories (Required and Optional)
**Required Must-have Stories**
- **User Registration and Login:** Users must be able to securely create an account and log in, ensuring that their data is protected and only accessible to them.
-  **Add Expense:** The system must allow users to record an expense by entering the amount, category, and date, enabling accurate tracking of spending.
- **View Expense History:** Users must be able to view a list of past expenses, sorted by date, to review and analyze their spending habits.
- **Expense Categories** The application should allow expenses to be assigned to predefined categories (e.g., food, rent, entertainment), providing users with insight into where they spend the most money.
- **Monthly Spending Summary:** A summary of total spending for the current month must be available, giving users a clear understanding of their overall budget performance.
- **Edit or Delete Expenses:** Users must have the ability to edit or delete any recorded expense, allowing for corrections or removal of inaccurate entries.
- **User Profile Management:** The system should provide users with the option to update personal details and change passwords within profile settings to keep their information secure and up to date.

**Optional Nice-to-have Stories**

- **Recurring Expenses:** The system should allow users to set up recurring expenses (e.g., rent, subscriptions), eliminating the need for manual entry each time.
- **Expense Filters and Search:** Users must have the ability to filter expenses by category, date, or amount, and search for specific transactions.
- **Spending Analytics and Graphs:** The application should provide visual representations of spending (e.g., pie charts, bar graphs) to break down expenses across different categories
- **Export Data:** Users should be able to export their expense data in formats such as CSV or PDF for use in other applications or for tax purposes..
- **Multi-Currency Support:** The system should support the tracking of expenses in multiple currencies, enabling users to manage spending during international travel.
- **Budget Goals:** Users must be able to set monthly or category-based budget goals, allowing them to monitor how well they are staying within their limits.
- **Notifications/Alerts:** The system should notify users when they are approaching budget limits for a category or when a bill is due.
- **Expense Sharing:** The application should allow users to share expenses with others (e.g., for splitting bills), ensuring that shared costs are tracked effectively.
- **Dark Mode:** A dark mode option should be available for the app interface to reduce eye strain during nighttime use.

### 2. Screens
- **Authentication Screen:** This screen will handle both the registration (sign-up) and login processes. It could have two separate forms or tabs for each action.
-  **Add/Edit Expense Screen:** Categories would be selectable on the Add Expense Screen using a dropdown or radio button selection. Predefined categories would be available on this screen.
This screen will allow users to input the amount, select a category, choose the date, and add any other details for the expense. It might also have a "Save" button to record the entry. 
- **Expense History Screen (with Edit/Delete options):** This screen will show a list of all the user's recorded expenses, possibly with sorting and filtering options. It will display the date, amount, category, and other relevant details for each transaction. The Expense History Screen will also allow users to tap on individual expenses to either edit or delete them. Upon selecting an expense, the app could open a detailed view where users can modify or remove the entry.
- **Dashboard Screen:** The dashboard will be the user's main landing page after logging in. It will show a high-level summary of the user's total monthly spending, with possibly a breakdown of spending by category. This screen could also display key insights, like "Top Category" or "Total Expenses."
-- **Profile Settings Screen:** This screen will allow users to view and update their profile information, including name, email, and password. It could also include an option to delete the account.

### 3. Navigation 
**Tab Navigation** (Tab to Screen)

- Dashboard (Overview of Expenses)
- Add Expense
- Profile
- Settings

Optional:
- Monthly Summary
- Analytics (Spending Breakdown)

**Flow Navigation** (Screen to Screen)

- Forced Log-in -> Redirect to account creation if no login is available
- Dashboard -> Navigate to Add Expense, Expense Details, or Monthly Summary
- Add Expense -> Save expense and return to Dashboard
- Profile -> Allows modification of user information
- Settings -> Toggle app settings (e.g., notifications, dark mode)

### 3. WireFrames 
![Untitled Notebook-2](https://github.com/user-attachments/assets/06734728-0b2c-4fee-86fb-5ec78defc158)


### 4. Schema
User:

Property	            Type	             Description 

id                     long              Unique identifier for the user   
name                   String            User's full name
email              String        User's email (used for login)
password           String        Hashed password for authentication
createdAt       LocalDateTime    Timestamp for account creation
updatedAt       LocalDateTime    Timestamp for account updates


Expense:
Property	         Type	          Description 
id                 long          Unique identifier for the user          
amount             double        The amount spent          
description        String        Description of the expense          
date               LocalDate     Date the expense was made          
category           category      Reference to the Category model         
user               user          Reference to the Category model         
createdAt          LocalDateTime  Timestamp for when the expense was added        
updatedAt          LocalDateTime  Timestamp for any updates to the expense



Category:
Property	         Type	          Description 
id                 long          Unique identifier for the user    
name               String        Category name (e.g., "Food," "Travel")
user               User          Optional reference to User for custom categories


Profile:
Property	         Type	          Description 
id                 long          Unique identifier for the user 
user               User          Optional reference to User for custom categories
currency           String        Preferred currency (e.g., "USD")
timeZone           String        User's time zone
darkMode           Boolean       Whether dark mode is enabled
    


Budget Model (Optional):
Property	         Type	          Description 
id                 long          Unique identifier for the user 
limit              BigDecimal    The maximum amount allowed for the budget
startDate          LocalDate     Start date for the budget period
endDate            LocalDate     End date for the budget period
category           Category      Reference to the Category model (optional)
user               User          Reference to the User model


RecurringExpense Model (Optional):
Property	         Type	          Description 
id                 long        Unique identifier for the recurring expense
amount             double      The amount of the recurring expense
description        String      Description of the recurring expense
startDate          LocalDate   Start date of the recurring expense
frequency          Frequency   Enum: DAILY, WEEKLY, MONTHLY, YEARLY
category           Category    Reference to the Category model (optional)
user               User        Reference to the User model



TransactionLog Model (Optional):
Property	         Type	          Description 
id                 long            Unique identifier for the log entry
expense            Expense         Reference to the related expense
user               User            Reference to the user who made the change
action             String          Type of action: "ADD", "EDIT", "DELETE"
timestamp          LocalDateTime   Timestamp for when the action occurred







