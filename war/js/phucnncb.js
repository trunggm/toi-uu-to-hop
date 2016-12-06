/*
 * http://stackoverflow.com/questions/18260815/use-gapi-client-javascript-to-execute-my-custom-google-api
 * https://developers.google.com/appengine/docs/java/endpoints/consume_js
 * https://developers.google.com/api-client-library/javascript/reference/referencedocs#gapiclientload
 *
 */

/**
 * After the client library has loaded, this init() function is called.
 * The init() function loads the helloworldendpoints API.
 */

function init() {

	// You need to pass the root path when you load your API
	// otherwise calls to execute the API run into a problem

	// rootpath will evaulate to either of these, depending on where the app is running:
	// //localhost:8080/_ah/api
	// //your-app-id/_ah/api
	changeInput();

	var rootpath = "//" + window.location.host + "/_ah/api";
	console.log(rootpath);
	// Load the helloworldendpoints API
	// If loading completes successfully, call loadCallback functionmô
	gapi.client.load('phucnnendpoint', 'v1', loadCallback, rootpath);
}

/*
 * When helloworldendpoints API has loaded, this callback is called.
 *
 * We need to wait until the helloworldendpoints API has loaded to
 * enable the actions for the buttons in index.html,
 * because the buttons call functions in the helloworldendpoints API
 */
function loadCallback () {
	// Enable the button actions
	enableButtons ();
}

function enableButtons () {
	// Set the onclick action for the first button
	// btn = document.getElementById("input_greet_generically");
	// btn.onclick= function(){greetGenerically();};

	// Update the button label now that the button is active
	// btn.value="Click me for a generic greeting";

	// Set the onclick action for the second button
	// btn = document.getElementById("input_greet_by_name");
	// btn.onclick=function(){greetByName();};

	// Update the button label now that the button is active
	// btn.value="Click me for a personal greeting";

	// Set the onclick action for the third button
	// btn = document.getElementById("input_greet_by_period");
	// btn.onclick=function(){greetByPeriod();};

	// Update the button label now that the button is active
	// btn.value="Click me for a period greeting";

	// set the onclick action for the sumbit button
	btn = document.getElementById("submit");
	btn.onclick=function () {
		changeInput();
		bl3();
	}
	btn.innerHTML="Click to Submit";
}

/*
 * Execute a request to the sayHello() endpoints function
 */
function bl3() {
	var input = $('#input-text').val();
	var request = gapi.client.phucnnendpoint.chessboard({'input': input});
	request.execute(bl3Callback);
}

function bl3Callback(response) {
	console.log(response);
	var bestPath = response.result.result;

	$('#best-cost').hide();
	$('#best-path').hide();
	$('#best-time').hide();
	$('.output-box').append(bestPath);
}

function changeInput() {
	$('.output-box').html('');
}