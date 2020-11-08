angular.module('bqTestModule', [])
    .controller('FetchController', ['$scope', '$http',
        function ($scope, $http) {

            fetchData();
			
            $scope.stateChanged = function(){
                $scope.statedata = $scope.selectedState;
                $scope.entrycount=$scope.selectedState.length;
            };

            function fetchData() {
                $http({method: 'GET', url: 'api/load-data'}).then(function (response) {
                    console.log(response.data);
		$scope.states_name=response.data;
                get_all_states(response.data);
                   
                }, function (reason) {
                    console.log('error ' + reason);
                });
            }
            
            function get_all_states(response){
                var allstates=[];
                var a=0;
                for (var x in response){
                    var b=0;
                    for (var y in Object.values(response)[a]){
                       allstates.push(Object.values(response)[a][b]);
                       b++;
                    }
                    a++;
                }
                $scope.statedata=allstates;
                $scope.entrycount=allstates.length;
            }

        }]);
