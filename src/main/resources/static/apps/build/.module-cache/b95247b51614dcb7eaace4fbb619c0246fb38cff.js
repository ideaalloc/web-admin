/**
 * Author: Bill Lv<billcc.lv@hotmail.com>
 * Date: 2015-08-26
 */
var KickStarterBox = React.createClass({displayName: "KickStarterBox",
    getInitialState: function () {
        return {data: this.props.data};
    },
    componentDidMount: function () {
        // do nothing
    },
    render: function () {
        return (
            React.createElement("div", null, 
                React.createElement(KickStartersList, {ksInfo: this.state.data}), 
                React.createElement("br", null)
            )
        );
    }
});

var KickStartersList = React.createClass({displayName: "KickStartersList",
    render: function () {
        var ksNodes = this.props.ksInfo.map(function (ks) {
            return (
                React.createElement("tr", null, 
                    React.createElement("td", null, ks.id), 
                    React.createElement("td", null, ks.owner), 
                    React.createElement("td", null, ks.category), 
                    React.createElement("td", null, ks.targetAmt), 
                    React.createElement("td", null, ks.deadline), 
                    React.createElement("td", null, ks.title), 
                    React.createElement("td", null, ks.createTime)
                )
            );
        });

        return (
            React.createElement("div", {className: "row finance-info"}, 
                React.createElement("div", {className: "col-xs-12 col-sm-12 col-md-12"}, 
                    React.createElement("h4", null, "众筹中的项目"), 

                    React.createElement("div", {className: "table-responsive"}, 
                        React.createElement("table", {className: "table"}, 
                            React.createElement("tr", {className: "active"}, 
                                React.createElement("th", null, "编号"), 
                                React.createElement("th", null, "发起人"), 
                                React.createElement("th", null, "类别"), 
                                React.createElement("th", null, "目标"), 
                                React.createElement("th", null, "截止日期"), 
                                React.createElement("th", null, "标题"), 
                                React.createElement("th", null, "创建时间")
                            ), 
                            ksNodes
                        )
                    )
                )
            )
        );
    }
});

var showKickStarters = function (data) {
    React.render(
        React.createElement(KickStarterBox, {data: data}),
        document.getElementById("kickStarters")
    );
};
