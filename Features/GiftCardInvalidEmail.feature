
Feature: Enter invalid email
@Sanity
	Scenario: After entering invalid email error message and screenshot should be captured
		Given user launch browser
		And opens the url "https://www.makemytrip.com/"
		When user clicks on gift card option
		And select wedding gift card
		When gives sender information with invalid email
		Then error is captured