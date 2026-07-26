# Bank Management System

A simple desktop banking application built with **Java Swing**, featuring an admin panel to create, manage, and track customer bank accounts. Account data is persisted locally using Java object serialization, so records survive between runs.

## Description

Bank Management System is a lightweight, single-window GUI application that simulates the core operations of a bank's back office. An admin can create new accounts, deposit and withdraw funds, transfer money between accounts, search for a specific account, view all accounts at a glance, delete accounts, and check the running total of transactions processed. All account records (name, address, account type, balance, and a unique account ID) are saved to `accounts.dat` and reloaded automatically the next time the app starts.

## Features

- 🏦 **Create Account** — Opens a form to capture name, address, account type, and initial balance. A unique account ID (UUID) is generated automatically.
- 💰 **Deposit** — Add funds to an existing account by ID.
- 💸 **Withdraw** — Withdraw funds from an account, with an insufficient-balance check.
- 🔄 **Transfer** — Move funds from one account to another, validated against the sender's balance.
- 🔍 **Search** — Look up an account by name or account ID.
- 📋 **Show All Accounts** — Display every account currently on record.
- 📊 **Total Transactions** — View a running count of all deposit/withdraw/transfer operations.
- 🗑️ **Delete Account** — Remove an account after a confirmation prompt.
- 💾 **Persistent Storage** — Accounts are serialized to `accounts.dat` on every change and reloaded on startup.

## Tech Stack

- **Language:** Java
- **GUI:** Java Swing (`JFrame`, `JOptionPane`, `JTextPane`, etc.)
- **Persistence:** Java Object Serialization (`ObjectOutputStream` / `ObjectInputStream`)

## Project Structure

```
Bank_Management_System/
├── BankAccount.java   # Core account model (fields, transfer/deposit/withdraw logic)
├── BankGUI.java        # Swing GUI, admin panel, and app entry point (main)
├── accounts.dat        # Serialized account data (auto-generated/updated at runtime)
├── icon.png            # Application window icon
└── README.md
```

## Getting Started

### Prerequisites

- JDK 8 or later installed and available on your `PATH`

### Compile

```bash
git clone https://github.com/ar1nd0m/Bank_Management_System.git
cd Bank_Management_System
javac BankGUI.java BankAccount.java
```

### Run

```bash
java BankGUI
```

The app opens in a maximized window titled **"Bank Management System"** with an admin panel of action buttons. Use the on-screen buttons to create accounts and perform transactions — all changes are saved automatically to `accounts.dat`.

## Notes

- This project stores plaintext balances and personal details in a local `.dat` file with no encryption or authentication — it's intended as a learning/demo project, not for production or real financial use.
- Account IDs are UUIDs, so use the **Search** or **Deposit/Withdraw/Transfer** dialogs and copy the ID from **Show All Accounts** to reference a specific account.

## License

No license specified. Feel free to open an issue or PR if you'd like one added.
