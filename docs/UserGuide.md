---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# AB-3 User Guide

AddressBook Level 3 (AB3) is a **desktop app for managing contacts, optimized for use via a  Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, AB3 can get your contact management tasks done faster than traditional GUI apps.

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.<br>
   **Mac users:** Ensure you have the precise JDK version prescribed [here](https://se-education.org/guides/tutorials/javaInstallationMac.html).

2. Download the latest `.jar` file from [here](https://github.com/se-edu/addressbook-level3/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar addressbook.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * `list` : Lists all contacts.

   * `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * `delete 3` : Deletes the 3rd contact shown in the current list.

   * `clear` : Deletes all contacts.

   * `exit` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

</box type="info" seamless>

**Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`


### Adding a person: `add`

Adds a person to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL tele/TELEGRAM pos/POSITION a/ADDRESS [t/TAG]…​ [s/SKILL]…​ [o/OTHER]…​ [task/TASK]…​`

</box type="tip" seamless>

**Tip:** A person can have any number of tags (including 0)
</box>

Examples:
* `add n/John Doe p/98765432 e/johnd@example.com tele/@john pos/student a/John street, block 123, #01-01 task/barbeque, 2025-05-28 14:00, yet to start`
  * `add n/Betsy Crowe t/friend e/betsycrowe@example.com tele/@besty pos/teacher a/Newgate Prison p/1234567 t/criminal task/barbeque, 2025-05-28 14:00, yet to start`
    ![input for 'add n/Betsy Crowe t/friend e/betsycrowe@example.com tele/@besty pos/teacher a/Newgate Prison p/1234567 t/criminal task/barbeque, 2025-05-28 14:00, yet to start'](images/addBestyCroweInput.png)
    ![result for 'add n/Betsy Crowe t/friend e/betsycrowe@example.com tele/@besty pos/teacher a/Newgate Prison p/1234567 t/criminal task/barbeque, 2025-05-28 14:00, yet to start'](images/addBestyCroweResult.png)

### Listing all persons : `list`

Shows a list of all persons in the address book.

Format: `list`

### Editing a person : `edit`

Edits an existing person in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`

* Edits the person at the specified `INDEX`. The index refers to the index number shown in the displayed person list. The index **must be a positive integer** 1, 2, 3, …​
* At least one of the optional fields must be provided.
* Existing values will be updated to the input values.
* When editing tags, the existing tags of the person will be removed i.e adding of tags is not cumulative.
* You can remove all the person’s tags by typing `t/` without
    specifying any tags after it.

Examples:
*  `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st person to be `91234567` and `johndoe@example.com` respectively.
*  `edit 2 n/Betsy Crower t/` Edits the name of the 2nd person to be `Betsy Crower` and clears all existing tags.

### Locating persons by name: `find`

Finding persons by name:

Format: `find n/ KEYWORD [MORE_KEYWORDS]`

Finds persons whose names contain any of the given keywords.

* The search is case-insensitive. e.g `hans` will match `Hans`
* The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
* Only the name is searched.
* Only full words will be matched e.g. `Han` will not match `Hans`
* Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:
* `find n/ john` returns `john` and `John Doe`
* `find n/ alex david` returns `Alex Yeoh`, `David Li`<br>
  ![result for 'find n/ bernice alex'](images/findAlexBerniceResult.png)

Finding persons by tags:

Format: `find t/ KEYWORD [MORE_KEYWORDS]`

Finds persons whose tags contain any of the given keywords.

Example Usage:
* `find t/ colleagues friends` returns all Persons tagged with colleagues or friends
  ![result for 'find t/ colleagues friends'](images/findColleaguesFriendsResult.png)

### Deleting a person : `delete`

Deletes the specified person from the address book.

Format: `delete INDEX`

* Deletes the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd person in the address book.
* `find n/ Betsy` followed by `delete 1` deletes the 1st person in the results of the `find` command.

### Listing tasks assigned to a member : `listtasks`

Lists the tasks of
the specified person from the address book.

Format: `listtasks INDEX`

* Lists the person at the specified `INDEX`.
* The index refers to the index number shown in the displayed person list.
* The index **must be a positive integer** 1, 2, 3, …​

Example results for member without task:
* ![listtasks for person without a task](images/listtasksAlex1.png)
* ![listtasks for person without a task](images/listtasksAlex2.png)

Example results for member with tasks:
* ![listtasks for person with a task](images/listtasksJungkook.png)

Examples:
* `list` followed by `listtasks 2` list the tasks the 2nd person in the address book has.
* `find n/ Alex` followed by `listtasks 1` lists the tasks of 1st person in the results of the `find` command.

### Updating status for a task : `mark`

Mark the task status for a specific task of a member.

Format: `mark PERSON_INDEX TASK_INDEX task_status`

* Set the due date for a task at `TASK_INDEX` of the person at the specified `PERSON_INDEX`.
* `TASK_INDEX` refers to the index number shown in the task list of a member.
* Both indexes **must be a positive integer** 1, 2, 3, …​
* Only valid inputs are only `yet-to-start`, `in-progress` and `completed`.

![mark a task in progress](images/markTaskResult.png)

Examples:
* `listtasks 2` followed by `mark 3 2 in-progress` sets the task status for the second task of the third person in the displayed person list to be **in-progress**.
* `find n/ Betsy` followed by `mark 2 1 completed` sets the task status for the first task of the second person in the results of the `find` command to be **completed**.

### Setting due date for a task : `setduedate`

Set a due date for a specific task of a member.

Format: `setduedate PERSON_INDEX taskint/TASK_INDEX due/yyyy-mm-dd hh:mm`

* Set the due date for a task at `TASK_INDEX` of the person at the specified `PERSON_INDEX`.
* `TASK_INDEX` refers to the index number shown in the task list of a member.
* Both indexes **must be a positive integer** 1, 2, 3, …​
* Due date must be inputted in the format of `yyyy-mm-dd hh:mm`.

![set due date for a task](images/setduedate.png)

Examples:
* `listtasks 2` followed by `setduedate 2 taskint/1 due/2025-10-10 23:59` set the due date for the first task of the second person in the displayed person list to be 2025-10-10 23:59. 
* `find n/ Betsy` followed by `setduedate 1 taskint/2 due/2025-10-10 23:59` set the due date for the second task of the first person in the results of the `find` command. to be 2025-10-10 23:59.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

</box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run.  Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

### Generate Task Status Report : `report`

Shows a summary of all tasks and their completion statuses.

Format: `report`

Example output:

![generate report for one task](images/report.png)

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous AddressBook home folder.

--------------------------------------------------------------------------------------------------------------------

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to manually restore the minimized Help Window.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action     | Format, Examples
-----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------
**Add**    | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`
**Clear**  | `clear`
**Delete** | `delete INDEX`<br> e.g., `delete 3`
**Edit**   | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`
**Find**   | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`
**List**   | `list`
**Help**   | `help`
