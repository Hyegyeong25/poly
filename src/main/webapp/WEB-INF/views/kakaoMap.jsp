<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>여러개 마커 표시하기</title>

    <script src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.12.4.min.js"></script>
</head>
<body>

실시간 자전거 API
<div id="map" style="width:100%;height:750px;">test</div>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5c2e09a5c5feb6bcb0ba492c4813b5f1"></script>
<%--<script>--%>
<%--    var mapContainer = document.getElementById('map'), // 지도를 표시할 div--%>
<%--        mapOption = {--%>
<%--            center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표--%>
<%--            level: 3 // 지도의 확대 레벨--%>
<%--        };--%>

<%--    var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다--%>

<%--    // 마커를 표시할 위치와 title 객체 배열입니다--%>
<%--    var positions = [--%>
<%--        {--%>
<%--            title: '카카오',--%>
<%--            latlng: new kakao.maps.LatLng(33.450705, 126.570677)--%>
<%--        },--%>
<%--        {--%>
<%--            title: '생태연못',--%>
<%--            latlng: new kakao.maps.LatLng(33.450936, 126.569477)--%>
<%--        },--%>
<%--        {--%>
<%--            title: '텃밭',--%>
<%--            latlng: new kakao.maps.LatLng(33.450879, 126.569940)--%>
<%--        },--%>
<%--        {--%>
<%--            title: '근린공원',--%>
<%--            latlng: new kakao.maps.LatLng(33.451393, 126.570738)--%>
<%--        }--%>
<%--    ];--%>

<%--    // 마커 이미지의 이미지 주소입니다--%>
<%--    var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";--%>

<%--    for (var i = 0; i < positions.length; i ++) {--%>

<%--        // 마커 이미지의 이미지 크기 입니다--%>
<%--        var imageSize = new kakao.maps.Size(24, 35);--%>

<%--        // 마커 이미지를 생성합니다--%>
<%--        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);--%>

<%--        // 마커를 생성합니다--%>
<%--        var marker = new kakao.maps.Marker({--%>
<%--            map: map, // 마커를 표시할 지도--%>
<%--            position: positions[i].latlng, // 마커를 표시할 위치--%>
<%--            title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다--%>
<%--            image : markerImage // 마커 이미지--%>
<%--        });--%>
<%--    }--%>
<%--</script>--%>


<script>

navigator.geolocation.getCurrentPosition(function(pos) {
    $.ajax({
        url: "/apitest",
        type:"get",
        data : {
            lat : pos.coords.latitude,
            lon : pos.coords.longitude
        },
        contentType: "application/json",
        success: function(data) {

                /** 학교를 기본 위치로 잡음 -> 나중엔 내가 있는 위치를 기본으로 잡아보자! */

                    // var longitude = 126.84239510324666; // 경도
                    // var latitude = 37.549944383590336; // 위도
                console.log(data);
                var latitude = data.lat;
                var longitude = data.lon;

                var container = document.getElementById('map'); //지도를 담을 영역의 DOM 레퍼런스

                // 마커를 표시할 위치와 title 객체 배열입니다
                var positions = new Array();
                for(var i = 0; i < data.bicycleList.length; i++){
                    positions.push(
                        {
                            title: data.bicycleList[i].stationName,
                            latlng : new kakao.maps.LatLng(data.bicycleList[i].stationLatitude, data.bicycleList[i].stationLongitude)
                        }
                    )
                }

                console.log(positions);
                var options = { //지도를 생성할 때 필요한 기본 옵션
                    center: new kakao.maps.LatLng(latitude, longitude), //지도의 중심좌표.
                    level: 5 //지도의 레벨(확대, 축소 정도)
                };

                var map = new kakao.maps.Map(container, options); //지도 생성 및 객체 리턴

                // 마커 이미지의 이미지 주소입니다
                var imageSrc = "../images/location.png";
                var imageSize = new kakao.maps.Size(35, 35);

                for (var i = 0; i < positions.length; i++) {

                    // 마커 이미지를 생성합니다
                    var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

                    // 마커를 생성합니다
                    var marker = new kakao.maps.Marker({
                        map: map, // 마커를 표시할 지도
                        position: positions[i].latlng, // 마커를 표시할 위치
                        title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
                        image: markerImage // 마커 이미지
                    });
                }
        },
        error: function() {
            console.log("실패!");
        }
    })
});


</script>
</body>
</html>