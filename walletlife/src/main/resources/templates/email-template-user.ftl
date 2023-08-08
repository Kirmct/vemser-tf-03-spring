<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Wallet Life</title>
    <style>
        body {
            background-color: #f0f0f0;
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
            background-color: #ffffff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }
        .header {
            text-align: center;
            padding: 20px 0;
            background-color: #1094AB;
            color: #ffffff;
            border-radius: 10px 10px 0 0;
        }
        .title {
            font-size: 24px;
            font-weight: bold;
            margin: 10px 0;
            color: #007bff;
        }
        .content {
            font-size: 16px;
            line-height: 1.6;
            color: #333333;
            text-align: center;,
        }
        .button {
            display: inline-block;
            background-color: #1094AB;
            color: #ffffff;
            padding: 12px 24px;
            text-decoration: none;
            border-radius: 5px;
            margin-top: 20px;
			margin-bottom: 20px;
        }
        .footer {
            text-align: center;
            padding: 30px 0; /* Aumentar o espaço entre o botão e o rodapé */
            background-color: #1094AB;
            color: #ffffff;
            border-radius: 0 0 10px 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <h1>Wallet Life</h1>
        </div>
        <div class="content">
            <p>Olá ${nome},</p>
            <p>${paragrafo}</p>
            <p>Qualquer dúvida fale com nosso suporte! ${suporte}</p>
            <a class="button" href="https://www.walletlife.com" target="_blank">Acessar Wallet Life</a>
        </div>
        <div class="footer">
            <p>Se deseja não receber mais nossos e-mails, <a href="#">clique aqui</a>.</p>
        </div>
    </div>
</body>
</html>
