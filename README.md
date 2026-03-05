# 🌐 Public IP Checker and Notifier

## 📌 Overview
Public IP Checker and Notifier is a lightweight application that monitors the public IP address of the connected network.  
It compares the current public IP with the previously stored IP and notifies the user via Telegram if a change is detected.

---

## 🚀 Features

✅ Automatic Public IP Detection

✅ Detects Public IP Changes

✅ Telegram Notification via Bot API

✅ Lightweight and Fast

✅ Simple Configuration using .ini file

✅ Secure Credential Storage using Encryption

✅ Log File Maintenance

---

## 📁 Repository
🔗 https://github.com/iamsidh/PublicIpChecker/tree/main

---

## ⚙️ How It Works

1. The application fetches the current public IP address from an external service.
2. It reads the previously stored public IP from a file.
3. It compares both IP addresses:
   - If **both IPs are the same**  
     → Logs a message indicating no change.
   - If **IPs are different**  
     → Sends the new public IP to Telegram using the Telegram Bot API.  
     → Updates the stored IP file with the new IP.

---

## 🛠️ Tech Stack

- Java  
- File Handling (for storing previous IP)  
- Telegram Bot API  
- Logging Mechanism  

---

## 🔑 Configuration

### 1️⃣ Create a Telegram Bot

Create a Telegram bot using **@BotFather** in Telegram.

After creating the bot you will receive:

* `BOT_TOKEN`

---

### 2️⃣ Get Your Chat ID

Send a message to your bot and obtain your **CHAT_ID** using a Telegram update API or bot tools.

---

### 3️⃣ Encrypt Credentials

For security reasons, **BOT_TOKEN** and **CHAT_ID** must be encrypted before adding them to the configuration file.

Use the **CryptoUtil** helper methods provided in the project.

Example:

```java
String encryptedToken = CryptoUtil.encrypt("YOUR_BOT_TOKEN");
String encryptedChatId = CryptoUtil.encrypt("YOUR_CHAT_ID");
```

---

### 4️⃣ Update Configuration File

Replace the values inside **`IpCheckConfig.ini`** with the encrypted values.

Example:

```
token=ENCRYPTED_BOT_TOKEN
userid=ENCRYPTED_CHAT_ID
```

---

### 5️⃣ Runtime Decryption

The application automatically decrypts the credentials during runtime using:

```java
CryptoUtil.decrypt()
```

This ensures sensitive information is **not stored in plain text**.
