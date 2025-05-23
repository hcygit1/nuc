export default {
  after: (field, [target]) => `*此项必须在${target}之后`,
  alpha_dash: (field) => `*此项能够包含字母数字字符，包括破折号、下划线`,
  alpha_num: (field) => `*此项 只能包含字母数字字符.`,
  alpha_spaces: (field) => `*此项 只能包含字母字符，包括空格.`,
  alpha: (field) => `*此项只能包含字母字符.`,
  before: (field, [target]) => `*此项必须在${target} 之前.`,
  between: (field, [min, max]) => `*此项必须在${min} ${max}之间.`,
  confirmed: (field, [confirmedField]) => `*此项不能和${confirmedField}匹配.`,
  date_between: (field, [min, max]) => `*此项必须在${min}和${max}之间.`,
  date_format: (field, [format]) => `*此项必须在在${format}格式中.`,
  decimal: (field, [decimals] = ['*']) => `*此项 必须是数字的而且能够包含${decimals === '*' ? '' : decimals} 小数点.`,
  digits: (field, [length]) => `*此项必须是数字，且精确到 ${length}数`,
  dimensions: (field, [width, height]) => `*此项必须是 ${width} 像素到 ${height} 像素.`,
  email: (field) => `*此项必须是有效的邮箱.`,
  ext: (field) => `*此项必须是有效的文件.`,
  image: (field) => `*此项必须是图片.`,
  in: (field) => `*此项必须是一个有效值.`,
  ip: (field) => `*此项必须是一个有效的地址.`,
  max: (field, [length]) => `*此项不能大于${length}字符.`,
  mimes: (field) => `*此项必须是有效的文件类型.`,
  min: (field, [length]) => `*此项必须至少有 ${length} 字符.`,
  not_in: (field) => `*此项必须是一个有效值.`,
  numeric: (field) => `*此项只能包含数字字符.`,
  regex: (field) => `*此项格式无效.`,
  required: (field) => `*此项不能为空.`,
  size: (field, [size]) => `*此项必须小于 ${size} KB.`,
  url: (field) => `*此项不是有效的url.`
}
