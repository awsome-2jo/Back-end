function getDateCode() {
    const today = new Date();
    const year = today.getFullYear();
    const month = today.getMonth() + 1;
    return year * 100 + month;
}

function getDateString() {
    function leftPad(value) {
        if (value >= 10) {
            return value;
        }
    
        return `0${value}`;
    }
    const today = new Date();
    const delimiter = '-';
    const year = today.getFullYear();
    const month = leftPad(today.getMonth() + 1);
    const day = leftPad(today.getDate());

    return [year, month, day].join(delimiter);
}