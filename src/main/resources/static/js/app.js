angular.module('SuggesterApp', [
    'ngMaterial'
])
    .controller('MainCtrl', function ($scope, $http, $mdToast, $mdConstant) {
        $scope.interests = [];

        $scope.keys = [
            $mdConstant.KEY_CODE.SPACE,
            $mdConstant.KEY_CODE.TAB,
            $mdConstant.KEY_CODE.COMMA,
            $mdConstant.KEY_CODE.ENTER,
            $mdConstant.KEY_CODE.RIGHT_ARROW
        ];

        $scope.saveInterests = function() {
            var saveThese = $scope.interests;

            $http.post('/api/interests', {
                interests: saveThese
            })
                .then(function successCb(response){
                    $scope.interests = [];
                    $mdToast.showSimple('Successfully saved interests');
                }, function errorCb(response){
                    $mdToast.showSimple("Failed to save interests");
                });
        }

        $scope.suggestInterests = function(searchText) {
            return $http.get('/api/interests/_suggest', {
                params: {
                    q: searchText
                }
            })
                .then(function(response){
                    return response.data;
                });
        };
    })
;