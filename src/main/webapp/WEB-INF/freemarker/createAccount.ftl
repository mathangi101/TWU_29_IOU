<!DOCTYPE html>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html" charset="UTF-8">
<html lang="en">
<head>
    <title> IOU </title>
    <link href="static/css/bootstrap.css" rel="stylesheet">
    <link href="static/css/default.css" rel="stylesheet">
</head>

<body>
<header class="topbar">
    <h3 class="fill" align="middle">
        <a>
            iOWEYOU--online calculator for expenses sharing.

        </a>
    </h3>
</header>
<div class="container">
    <div class="login-form">
        <h2>Create Account</h2>

        <form name="account_form" action="createAccount" method="POST">
            <fieldset>
                <table>
                    Please enter some registration details below so that we can set you up with an iOWEYOU account.
                    Once you've registered you'll be able to log in and get started.
                    <tr>
                        <td><label for="email" id="email_label" name="email_label"> E-mail Address *: </label>
                        </td>
                        <td><input type="text" id="email" name="email"/></td>
                    </tr>
                    <tr>
                        <td><label for="username" id="name_label" name="name_label"> Name *: </label></td>
                        <td><input type="text" id="username" name="username"/></td>
                    </tr>

                    <tr>
                        <td><label for="password" id="password_label" name="password_label"> Password
                            *: </label></td>
                        <td><input type="password" id="password" name="password"/></td>
                    </tr>

                    <tr>
                        <td><label for="phoneNumber" id="phone_number_label" name="phone_number_label"> Phone
                            Number: </label></td>
                        <td><input type="text" id="phoneNumber" name="phoneNumber"/></td>
                    </tr>

                    <tr>
                        <td><input class="btn btn-primary" type="submit" name="createAccountButton"
                                   id="createAccountButton" value="Create Account"/></td>
                        <td><input class="btn btn-primary" type="button" name="cancelButton" id="cancelButton"
                                   value="Cancel"
                                   onclick="javascript:window.location.href='http://localhost:9130/twu/login'"/>
                        </td>
                    </tr>
                </table>
            </fieldset>
        </form>
    </div>
</div>
<!-- /container -->
</body>

</html>