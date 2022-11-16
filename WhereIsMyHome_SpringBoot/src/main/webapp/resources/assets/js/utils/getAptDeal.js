// 공공데이터 가져와서 처리 : apartment
const aptList = document.querySelector(`#data-list-apartment`);

const getAptDeal = async (date) => {
    let url =
        "http://openapi.molit.go.kr/OpenAPI_ToolInstallPackage/service/rest/RTMSOBJSvc/getRTMSDataSvcAptTradeDev"; /*URL*/
    let queryParams = "?" + encodeURIComponent("serviceKey") + "=" + keys.aptDeal; /*Service Key*/
    queryParams += "&" + encodeURIComponent("LAWD_CD") + "=" + encodeURIComponent(regcode.replace('*','').substring(0, 5)); /**/
    queryParams += "&" + encodeURIComponent("DEAL_YMD") + "=" + encodeURIComponent(date); /**/

    let result = null;

    await fetch(url + queryParams)
        .then((res) => res.text())
        .then((res) => {
            let xmlNode = new DOMParser().parseFromString(res, "text/xml");
            let data = xmlToJson(xmlNode).response.body;
            result = data.items.item;
        });
    
    return result;
};