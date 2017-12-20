coreModule.provider("urlProvider", function () {

    var rootURL = '/rest/';

    var randomizeURL = rootURL + 'randomize/{0}';
    var getAllUsersURL = rootURL + 'read/all';

    return {
	    $get: function () {
            return {
                GetServiceURL: function(serviceConstantURLName, paramsArray){
                    var requestURL = eval(serviceConstantURLName);

                    for(var i = 1; i <= paramsArray.length; i++) {
                        requestURL = requestURL.replace(new RegExp('\\{' + (i-1) + '\\}', 'gi'), paramsArray[i-1]);
                    }

                    return requestURL;
                }
            }
        }
	};
});
