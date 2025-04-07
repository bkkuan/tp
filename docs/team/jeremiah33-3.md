<!--
layout: page
title: Wei Tianyue's Project Portfolio Page
-->

### Project: TeamScape

TeamScape is designed for team managers, leaders, and student leaders who need an efficient way to track and manage their team members' task progress and status in an offline, single-user environment. It is a desktop address book application that helps user to see contacts as well as manage tasks under them. The user interacts with it using a CLI, and it has a GUI created with JavaFX.

Given below are my contributions to the project.

* **New Feature**: Set Due Date for Tasks
    * What it does: Allows setting a due date for a task via the `setduedate` command.
    * Justification: Tasks often come with deadlines. This feature makes the task manager more practical.
    * Highlights: This command is crucial to help managers organise their tasks as they check for due dates. Key to future enhancements features such as reminders and report by due date.
    * Credits: Consulted ChatGPT for doing date parsing.

* **New Feature**: Delete Task Command
  * What it does: Enables deletion of tasks from the task list using a specific command.
  * Justification: Sometimes, managers want to remove a task assigned under a member when the member requested for it. Thus, this feature allows for more flexibility.
  * Highlights: If the task is added wrongly, managers can 'redo' the command as well.

* **New Feature**: List Tasks by Member
  * What it does: Lists all tasks assigned to a specific member using a new command.
  * Justification: This is a fast command to allow managers to quickly view all tasks assigned under a member in a broad overview.
  * Highlights: This is useful if the managers just want to get a sense of the tasks under a member.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2425s2.github.io/tp-dashboard/?search=&sort=groupTitle&sortWithin=title&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=true&checkedFileTypes=functional-code&since=2025-02-21&tabOpen=true&tabType=authorship&tabAuthor=Jeremiah33-3&tabRepo=AY2425S2-CS2103-F09-4%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=functional-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

* **Project management**:
    * Managed releases `v1.4` - `v1.6` (3 releases) on GitHub
    * Used forking workflow to ensure a working copy of the application in the team's repository branch, while also reducing the confusion of having too many branches in the team repository.

* **Testing**:
  * Added unit tests for:
    * SetDueDateCommandParser 
    * Listing member tasks 
    * DeleteTaskCommandParser 
      * Updated test cases to support edge cases like past due dates and missing fields.
  * Overall increase in test coverage. (From 88% to 92%)

* **Documentation**:
    * User Guide:
        * Added documentation for the features `deltask`, `listtasks` and `setduedate` .
        * Did cosmetic tweaks to existing documentation.
        * Add in proposed application introduction and target users.
    * Developer Guide:
        * Added testing instructions for manual testing
        * Added use cases to the application and improved upon formatting and wording.
        * Generate table of contents with links to section.
        * Edit UML diagrams to represent the application better.
        * Generate table of contents with links to section.

* **Community**:
    * PRs reviewed (with non-trivial review comments).
    * Contributed to forum discussions.
    * Reported bugs and suggestions for other teams in the class.
