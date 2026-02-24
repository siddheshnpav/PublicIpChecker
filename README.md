# 🌐 Public IP Checker and Notifier

## 📌 Overview
Public IP Checker and Notifier is a lightweight application that monitors the public IP address of the connected network.  
It compares the current public IP with the previously stored IP and notifies the user via Telegram if a change is detected.

---

## 🚀 Features

- ✅ Automatic Public IP Detection  
- ✅ IP Change Comparison  
- ✅ Log File Maintenance  
- ✅ Telegram Notification via Bot API  
- ✅ Lightweight and Easy to Configure  

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

---

## 🔑 Configuration

1. Create a Telegram Bot using **@BotFather**.
2. Get your:
   - `BOT_TOKEN`
   - `CHAT_ID`
3. Add them inside your application configuration.

Example:
```java
String botToken = "YOUR_BOT_TOKEN";
String chatId = "YOUR_CHAT_ID";
