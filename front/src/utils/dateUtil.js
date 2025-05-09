export function transformDate(date) {
    if (date) {
      let dt = new Date(date);
      return dt.getFullYear() + '-' +
        ((dt.getMonth() + 1) < 10 ? ('0' + (dt.getMonth() + 1)) : (dt.getMonth() + 1)) + '-' +
        (dt.getDate() < 10 ? ('0' + dt.getDate()) : dt.getDate()) + ' ' +
        (dt.getHours() < 10 ? ('0' + dt.getHours()) : dt.getHours()) + ':' +
        (dt.getMinutes() < 10 ? ('0' + dt.getMinutes()) : dt.getMinutes()) + ':' +
        (dt.getSeconds() < 10 ? ('0' + dt.getSeconds()) : dt.getSeconds())
    } else {
      return ''
    }
  }
