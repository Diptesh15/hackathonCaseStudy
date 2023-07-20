Feature: Open hotel page link
@Sanity
	Scenario: Redirect to the hotel webpage
		Given user launch browser
		And opens the url "https://www.makemytrip.com/"
		When user clicks on hotel weblink
		Then user redirects to the hotel page
