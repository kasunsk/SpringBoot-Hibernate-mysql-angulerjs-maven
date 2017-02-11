app.controller('usersController', function ($scope) {
    $scope.headingTitle = "User List";
});

app.controller('rolesController', function ($scope) {
    $scope.headingTitle = "Roles List";
});

app.controller('loginController', function ($scope) {
    $scope.headingTitle = "Login";
});


app.controller('registerController', ['$scope','$http', '$location', function ($scope, $http, $location) {
    $scope.headingTitle = "Register";

    $scope.user = {
        name: null,
        email:null,
        password:null
    };

    $scope.saveUser = function () {
        $scope.submitting = true;
        $http({
            method: 'POST',
            url: '/user/save',
            data: $scope.user
        }).success(function (data) {
            $scope.submitting = false;
            location.path('/views/login.html');
            
        }).error(function (data, status) {
            $scope.submitting = false;
            if (status === 400)
                $scope.badRequest = data;
            else if (status === 409)
                $scope.badRequest = 'The name is already used.';
        });
    };
}]);

