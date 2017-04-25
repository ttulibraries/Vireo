vireo.directive("draganddroplist", function() {
	return {
		templateUrl: function(elem, attr) {
			if(attr.listView !== undefined) {
				return attr.listView;
			} else {
				return 'views/directives/dragAndDropList.html';
			}
		},
		restrict: 'E',
		scope: {
			'dragging': '=',
			'scopeValue': '=',
			'listeners': '=',
			'edit': '&',
			'toString': '&',
			'itemView': '@',
			'sortLabel': '@',
			'sortColumn': '@',
			'sortAction': '=',
			'sortActionSort': '=',
			'sortMethod': '&',
            'filter': '&',
            'activeFilter': '=?',
			'isEditable': '&'
		},
		controller: function($scope) {

            $scope.activeFilterText = {};

            $scope.setSelectedFilter = function(filter) {
                $scope.selectedFilter = filter;
            };

			if(typeof $scope.itemView == 'undefined') {
				$scope.itemView = 'views/directives/dragAndDropItem.html';
			}
		},
		link: function($scope, elem, attr) {
			$scope.properties = angular.fromJson(attr.properties);
            $scope.selectedFilter = $scope.properties.length==1?$scope.properties[0]:"";
		}
	};
});
