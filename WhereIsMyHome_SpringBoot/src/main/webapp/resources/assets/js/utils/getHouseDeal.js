// 공공데이터 가져와서 처리 : apartment
const houseList = document.querySelector(`#data-list-house`);

const getHouseDeal = async (date) => {
    let url =
        "http://openapi.molit.go.kr:8081/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcSHTrade"; /*URL*/
    let queryParams = "?" + encodeURIComponent("serviceKey") + "=" + keys.houseDeal; /*Service Key*/
    queryParams += "&" + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(regcode.replace('*','').substring(0, 5)); /**/
    queryParams += "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(date); /**/

    let result = null;

    await fetch(url + queryParams)
        .then((res) => res.text())
        .then((res) => {
            let xmlNode = new DOMParser().parseFromString(res, "text/xml");
            let data = xmlToJson(xmlNode).response.body;
            console.log(data);
            result = data.items?.item ?? [];
        });
    
    return result;
};