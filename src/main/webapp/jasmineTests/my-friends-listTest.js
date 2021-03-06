describe("Email list table", function () {
    var input = $('<input type="email" id="newEmail">');
    var emailTable = $(

        '<div id="emailListDiv">' +
            '<table id="emailList" border="2">' +
            '<tr style="background-color: #87cefa;">' +
            '<th id="header">Friends Added</th>' +
            '</tr>' +
            '<tr>' +
            '<td id="baseRow"> &nbsp; </td>' +
            '</tr>' +
            '</table>' +
            '</div>');

    var ErrorMessage = $('<div>' +
        '<p id="badEmailNotification" class="text-error">TEST</p>' +
        '</div>');

    var script = $('<script type="text/javascript">' +
        'var user = "sajacobs@thoughtworks.com";' +
        '</script>');


    var row0;
    var row1;
    var row2;

    beforeEach(function () {
        $(document.body).append(input);
        $(document.body).append(emailTable);
        $(document.body).append(ErrorMessage);
        $(document.body).append(script);

    });

    afterEach(function () {
        $('#emailListDiv').remove();
        $('#newEmail').remove();
        $('#badEmailNotification').innerText == "";
        rowCounter = 1;
        emailsToAdd = "";
    });

    it("should be updated below Header when table empty", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";

        var table = document.getElementById('emailList');
        row0 = table.rows[0].innerText;
        expect(row0).toMatch("Friends Added");

        addToList();

        row1 = table.rows[1].innerText;
        expect(row0).toMatch("Friends Added");
        expect(row1).toMatch("abc@gmail.com");
    });


    it("should be updated at the top", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";

        addToList();

        document.getElementById('newEmail').value = "xyz@gmail.com";

        addToList();

        var table = document.getElementById('emailList');
        row0 = table.rows[0].innerText;
        row2 = table.rows[1].innerText;
        row1 = table.rows[2].innerText;

        expect(row0).toMatch("Friends Added");
        expect(row2).toMatch("xyz@gmail.com");
        expect(row1).toMatch("abc@gmail.com");

    });

    it("should clear the table when click save", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";

        addToList();

        document.getElementById('newEmail').value = "xyz@gmail.com";

        addToList();

        save();

        var table = document.getElementById('emailList');
        row0 = table.rows[0].innerText;
        row1 = table.rows[1].innerText;


        expect(row0).toMatch("Friends Added");
        expect(row1).toMatch(String.fromCharCode(160));  //(&nbsp;)

    });

    it("should clear input on save", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";
        save();

        expect(document.getElementById('newEmail').value).toBe("");
    });

    it("should clear input on add to list", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";
        addToList();

        expect(document.getElementById('newEmail').value).toBe("");
    });

    it("should reset the row counter on clearTable", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";
        addToList();
        document.getElementById('newEmail').value = "xyz@gmail.com";
        addToList();

        expect(rowCounter).toEqual(3);

        clearTable();

        expect(rowCounter).toEqual(1);

    });

    it("should not add to table if input empty", function () {
        document.getElementById('newEmail').value = "";
        var tableBefore = document.getElementById('emailList');

        addToList();

        var tableAfter = document.getElementById('emailList');


        expect(tableAfter).toEqual(tableBefore);


    });

    it("should not increment rowCounter on add if input empty", function () {
        document.getElementById('newEmail').value = "";
        addToList();

        expect(rowCounter).toEqual(1);


    });

    it("should add the emails to the string", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";
        addToList();
        document.getElementById('newEmail').value = "xyz@gmail.com";
        addToList();
        expect(emailsToAdd).toEqual("abc@gmail.com,xyz@gmail.com,");
    });

    it("should clear the string on save", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";

        addToList();

        document.getElementById('newEmail').value = "xyz@gmail.com";

        addToList();

        save();
        expect(emailsToAdd).toEqual("");
    });

    it("should display error if email already in list", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";

        addToList();

        document.getElementById('newEmail').value = "abc@gmail.com";
        addToList();


        expect(document.getElementById('badEmailNotification').innerText).toBe("Email already in the list.");

    });


    it("should not Add email to emailsToAdd if email already in list in different case", function () {
        document.getElementById('newEmail').value = "abc@gmail.com";

        addToList();

        document.getElementById('newEmail').value = "ABC@gmail.com";
        addToList();


        expect(document.getElementById('badEmailNotification').innerText).toBe("Email already in the list.");

    });

    it("should not incremenet rowCounter if email already in list", function () {
        expect(rowCounter).toEqual(1);
        document.getElementById('newEmail').value = "abc@gmail.com";
        addToList();

        expect(rowCounter).toEqual(2);
        document.getElementById('newEmail').value = "abc@gmail.com";

        addToList();

        expect(rowCounter).toEqual(2);

    });

    it("should display error if try to add self", function () {
        document.getElementById('newEmail').value = "sajacobs@thoughtworks.com";    //hardcoding self :\
        addToList();

        expect(document.getElementById('badEmailNotification').innerText).toBe("You cannot add yourself.")

    });

    it("should not increment rowCounter if try to add self", function () {
        expect(rowCounter).toEqual(1);
        document.getElementById('newEmail').value = "sajacobs@thoughtworks.com";
        addToList();

        expect(rowCounter).toEqual(1);
    });

    it("should not Add email to emailsToAdd if try to add self", function () {
        document.getElementById('newEmail').value = "sajacobs@thoughtworks.com";

        addToList();


        expect(emailsToAdd.indexOf("sajacobs@thoughtworks.com")).toBe(-1);

    });


});

describe("Email validation", function () {

    var input = $('<input type="email" id="newEmail">');
    var emailTable = $(

        '<div id="emailListDiv">' +
            '<table id="emailList" border="2">' +
            '<tr style="background-color: #87cefa;">' +
            '<th id="header">Friends Added</th>' +
            '</tr>' +
            '<tr>' +
            '<td id="baseRow"> &nbsp; </td>' +
            '</tr>' +
            '</table>' +
            '</div>');

    var ErrorMessage = $('<div>' +
        '<p id="badEmailNotification" class="text-error">TEST</p>' +
        '</div>');


    beforeEach(function () {
        $(document.body).append(input);
        $(document.body).append(emailTable);
        $(document.body).append(ErrorMessage);

    });

    afterEach(function () {
        $('#emailListDiv').remove();
        $('#newEmail').remove();
        $('#badEmailNotification').remove();
        rowCounter = 1;
        emailsToAdd = "";
    });

    it("should return true if email is valid", function () {
        document.getElementById('newEmail').value = "test@gmail.com";

        expect(validateNewEmail()).toBeTruthy();
    });


    it("should return false if email has no @", function () {
        document.getElementById('newEmail').value = "testgmail.com";

        expect(validateNewEmail()).toBeFalsy();
    });


    it("should return false if email has no .", function () {
        document.getElementById('newEmail').value = "test@gmailcom";

        expect(validateNewEmail()).toBeFalsy();
    });

    it("should return false if @ comes after .", function () {
        document.getElementById('newEmail').value = "test.gmail@com";

        expect(validateNewEmail()).toBeFalsy();
    });


    it("should return false if email has no text before @", function () {
        document.getElementById('newEmail').value = "@gmail.com";

        expect(validateNewEmail()).toBeFalsy();
    });

    it("should return false if email has no text adter @", function () {
        document.getElementById('newEmail').value = "dog@";

        expect(validateNewEmail()).toBeFalsy();
    });
});
