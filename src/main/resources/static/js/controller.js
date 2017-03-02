app.controller('usersController', ['$scope', '$http', '$window', function ($scope, $http, $window) {
    $scope.headingTitle = "User List";
    $scope.clicked = false;

    $scope.userList = function () {
        $scope.submitting = true;
        $http({
            method: 'GET',
            url: '/user/list'
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
            url: '/user/remove/' + userId
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

app.controller('ticketController', ['$scope', '$http', '$cookies', '$window', function ($scope, $http, $cookies, $window) {
    $scope.headingTitle = "My Tickets";

    var applicantId = $cookies.get('applicantId');

    $scope.getMyTickets = function () {

        var myTicketUrl = '/' + applicantId + '/gammaairlines/tickets';

        $scope.submitting = true;
        $http({
            method: 'GET',
            url: myTicketUrl
        }).success(function (data) {
            $scope.myTickets = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = 'The name is already used.';
        });
    };

    $scope.viewTicket = function (idx) {

        if ($scope.myTickets != null) {
            $scope.userTicket = $scope.myTickets[idx];
        } else {
            $scope.userTicket = $scope.usersTickets[idx];
        }
        $window.location.href = '#/ticket';
    };
}]);

app.controller('loginController', ['$scope', '$http', '$cookies', '$window', function ($scope, $http, $cookies, $window) {

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
            $cookies.put('applicantName', data.name);
            $cookies.put('applicantRole', data.role);
            $window.location.href = 'homepage.html#/airlineOffer';
            $scope.applicantName = data.name;

            if (data.role == 'ADMIN') {
                $scope.isAdmin = true;
            } else {
                $scope.isAdmin = false;
            }

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

    $scope.logout = function () {
        $cookies.remove('applicantId');
        $cookies.remove('applicantName');
        $cookies.remove('applicantRole');
        $window.location.href = '/index.html';
    }

}]);


app.controller('registerController', ['$scope', '$http', '$window', function ($scope, $http, $window) {
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
            $window.location.href = '/index.html#/login';

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = 'The name is already used.';
        });
    };
}]);

app.controller('accountCreateController', ['$scope', '$http', '$cookies', '$window', '$timeout', function ($scope, $http, $cookies, $window, $timeout) {
    $scope.headingTitle = "My Accounts";
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
            url: accountUrl
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
            url: accountUrl
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

app.controller('airlineOfferController', ['$scope', '$http', '$cookies', '$window', function ($scope, $http, $cookies, $window) {
    $scope.headingTitle = "Available Offers";

    var applicantRole = $cookies.get('applicantRole');
    var applicantId = $cookies.get('applicantId');
    var requestUrl = '/' + applicantId + '/gammaairlines/offers';

    if (applicantRole == 'ADMIN') {
        $scope.isAdmin = true;
    } else {
        $scope.isAdmin = false;
    }

    $scope.availableAirlineOffers = function () {
        $scope.submitting = true;
        $http({
            method: 'GET',
            url: requestUrl
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
            url: airportListUrl
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

    $scope.select = function (idx) {

        $scope.offer_to_buy = $scope.availableOffers[idx];
        $scope.displayAccounts = true;
    };


    $scope.buy = function (account) {

        var rout_to_buy = $scope.offer_to_buy.route;
        var applicantId = $cookies.get('applicantId');
        var accountId = '' + account.accountId;

        $scope.buyingRequest = {
            accountId: accountId,
            ticketAmount: 1,
            airlineRout: rout_to_buy
        };

        var buyingRequestUrl = '/' + applicantId + '/gammaairlines/offers/buy';

        $scope.submitting = true;
        $http({
            method: 'POST',
            url: buyingRequestUrl,
            data: $scope.buyingRequest
        }).success(function (data) {
            $scope.submitting = false;
            $scope.userTicket = data;
            $window.location.href = '#/ticket';

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

app.controller('homeController', ['$scope', '$http', '$cookies', '$window', function ($scope, $http, $cookies, $window) {

    var applicantName = $cookies.get('applicantName');
    var applicantRole = $cookies.get('applicantRole');

    $scope.applicantName = applicantName;

    if (applicantRole == 'ADMIN') {
        $scope.isAdmin = true;
    } else {
        $scope.isAdmin = false;
    }
}]);

app.controller('usersTicketsController', ['$scope', '$http', '$cookies', '$window', function ($scope, $http, $cookies, $window) {
    $scope.headingTitle = "Users Tickets";

    var applicantId = $cookies.get('applicantId');
    var applicantRole = $cookies.get('applicantRole');

    if (applicantRole == 'ADMIN') {
        $scope.isAdmin = true;
    } else {
        $scope.isAdmin = false;
    }

    $scope.loadUserTickets = function (userId) {

        var userTicketsUrl = '/' + applicantId + '/gammaairlines/tickets/' + userId;

        $scope.submitting = true;
        $http({
            method: 'GET',
            url: userTicketsUrl,
        }).success(function (data) {
            $scope.submitting = false;
            $scope.usersTickets = data;

        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = '';
        });
    };

}]);

app.controller('moneyExchangeController', ['$scope', '$http', '$cookies', '$window', function ($scope, $http, $cookies, $window) {

    $scope.headingTitle = "Money Exchange";

    var applicantId = $cookies.get('applicantId');
    var applicantRole = $cookies.get('applicantRole');

}]);
