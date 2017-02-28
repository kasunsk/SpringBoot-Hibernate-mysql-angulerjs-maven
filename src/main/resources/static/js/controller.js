app.controller('usersController', ['$scope', '$http', '$window', function ($scope, $http, $window) {
    $scope.headingTitle = "User List";
    $scope.clicked = false;

    $scope.userList = function () {
        $scope.submitting = true;
        $http({
            method: 'GET',
            url: '/user/list',
        }).success(function (data) {
            $scope.clicked = true;
            $scope.allUsers = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = 'The name is already used.';
        });
    };

    $scope.deleteUser = function (idx) {

        var user_to_delete = $scope.allUsers[idx];
        var userId = user_to_delete.userId;

        $scope.submitting = true;
        $http({
            method: 'GET',
            url: '/user/remove/' + userId,
        }).success(function (data) {
            $scope.allUsers = $scope.userList();

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = 'The name is already used.';
        });
    };
}]);

app.controller('rolesController', function ($scope) {
    $scope.headingTitle = "Roles List";
});

app.controller('loginController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {

    $scope.headingTitle = "Login";

    $scope.loginRequest = {
        email: null,
        password: null
    };

    $scope.login = function () {
        $scope.submitting = true;
        $http({
            method: 'POST',
            url: '/user/login',
            data: $scope.loginRequest
        }).success(function (data) {
            $scope.submitting = false;
            $cookies.put('applicantId', data.userId);

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

}]);


app.controller('registerController', ['$scope', '$http', function ($scope, $http) {
    $scope.headingTitle = "Register";

    $scope.user = {
        name: null,
        email: null,
        password: null
    };

    $scope.saveUser = function () {
        $scope.submitting = true;
        $http({
            method: 'POST',
            url: '/user/save',
            data: $scope.user
        }).success(function (data) {
            $scope.submitting = false;
            $scope.allUsers = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = 'The name is already used.';
        });
    };
}]);

app.controller('accountCreateController', ['$scope', '$http', '$cookies', '$timeout', function ($scope, $http, $cookies, $timeout) {
    $scope.headingTitle = "Create Account";
    $scope.showSuccessAlert = false;


    $scope.availableCurrencies = function () {
        $http({
            method: 'GET',
            url: '/account/availableCurrency'
        }).success(function (data) {
            $scope.currencies = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    var applicantId = $cookies.get('applicantId');
    var accountCreateUrl = '/' + applicantId + '/paypallets/account';

    $scope.accountCreateRequest = {
        currency: null
    };


    $scope.createAccount = function () {
        $http({
            method: 'POST',
            url: accountCreateUrl,
            data: $scope.accountCreateRequest
        }).success(function (data) {
            $scope.successTextAlert = "Bank account created successfully!";
            $scope.showSuccessAlert = true;
            //$timeout($scope.showSuccessAlert = true, 3000);
            $scope.bankAccount = data;
            $scope.accountList();

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.depositRequest = {
        accountId: null,
        price: null
    };

    $scope.deposit = function (account) {

        $scope.depositRequest.accountId = account.accountId;
        $scope.depositRequest.price.currency = account.currency;

        var depositUrl = '/' + applicantId + '/paypallets/account/deposit/';

        $http({
            method: 'POST',
            url: depositUrl,
            data: $scope.depositRequest
        }).success(function (data) {
            $scope.successDepositeTextAlert = "Money deposited successfully!";
            $scope.showDepositeSuccessAlert = true;
            $scope.accountList();
            $scope.displayDeposit = false;
        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.showInput = function (idx) {
        var accountId = $scope.allAccounts[idx];
        var currency = $scope.allAccounts[idx].currency;

        $scope.displayDeposit = true;
        $scope.depositAccountId = accountId;
        $scope.accountCurrency = currency;
    };

    $scope.accountList = function () {
        var accountUrl = '/' + applicantId + '/paypallets/account/all';
        $http({
            method: 'GET',
            url: accountUrl,
        }).success(function (data) {
            $scope.showViewAccounts = true;
            $scope.allAccounts = data;
        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.deleteAccount = function (idx) {

        var accountId = $scope.allAccounts[idx].accountId;
        var accountUrl = '/' + applicantId + '/paypallets/account/remove/' + accountId;

        $http({
            method: 'GET',
            url: accountUrl,
        }).success(function (data) {
            $scope.accountList();
        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };
}]);

app.controller('airlineOfferController', ['$scope', '$http', '$cookies', function ($scope, $http, $cookies) {
    $scope.headingTitle = "Available Offers";

    var applicantId = $cookies.get('applicantId');
    var requestUrl = '/' + applicantId + '/gammaairlines/offers';

    $scope.availableAirlineOffers = function () {
        $scope.submitting = true;
        $http({
            method: 'GET',
            url: requestUrl,
        }).success(function (data) {
            $scope.submitting = false;
            $scope.availableOffers = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.init = function () {

        var airportListUrl = '/gammaairlines/country/all';

        $http({
            method: 'GET',
            url: airportListUrl,
        }).success(function (data) {
            $scope.availableAirports = data;

        }).error(function (data, status) {
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });

        $http({
            method: 'GET',
            url: '/account/availableCurrency'
        }).success(function (data) {
            $scope.currencies = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.select = function(idx) {

        $scope.offer_to_buy = $scope.availableOffers[idx];
        $scope.displayAccounts = true;
        //$scope.accountList();

    };


    $scope.buy = function (account) {

        //var offer_to_buy = $scope.availableOffers[idx];
        var rout_to_buy = $scope.offer_to_buy.route;
        var applicantId = $cookies.get('applicantId');
        var accountId = ''+account.accountId;

        var buyingRequest = {

            accountId : accountId,
            ticketAmount : 1,
            airlineRout : rout_to_buy

        };

        var buyingRequestUrl = '/' + applicantId + '/gammaairlines/offers/buy';

        $scope.submitting = true;
        $http({
            method: 'POST',
            url: buyingRequestUrl,
            data: buyingRequest
        }).success(function (data) {
            $scope.submitting = false;
            $scope.availbaleOffers = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.remove = function (idx) {

        var offerIdToRemove = $scope.availableOffers[idx];

        var removeRequestUrl = '/gammaairlines/offer/remove/' + offerIdToRemove.offerId;

        $scope.submitting = true;
        $http({
            method: 'GET',
            url: removeRequestUrl
        }).success(function (data) {
            $scope.submitting = false;
            $scope.availableAirlineOffers();

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.addAirlineOffer = function (offer) {

        var createAirlineOfferRequestUrl = '/gammaairlines/offers/save';

        $scope.submitting = true;
        $http({
            method: 'POST',
            url: createAirlineOfferRequestUrl,
            data: offer
        }).success(function (data) {
            $scope.submitting = false;
            $scope.availableAirlineOffers();

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };
}]);

