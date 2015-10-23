/**
 * Author: Bill Lv<billcc.lv@hotmail.com>
 * Date: 2015-08-26
 */
var KickStarterBox = React.createClass({
    getInitialState: function () {
        return {data: this.props.data};
    },
    componentDidMount: function () {
        // do nothing
    },
    render: function () {
        return (
            <div>
                <KickStartersList ksInfo={this.state.data}/>
                <br/>
            </div>
        );
    }
});

var KickStartersList = React.createClass({
    render: function () {
        var ksNodes = this.props.ksInfo.map(function (ks) {
            return (
                <tr>
                    <td>{ks.id}</td>
                    <td>{ks.owner}</td>
                    <td>{ks.category}</td>
                    <td>{ks.targetAmt}</td>
                    <td>{ks.deadline}</td>
                    <td>{ks.title}</td>
                    <td>{ks.createTime}</td>
                </tr>
            );
        });

        return (
            <div className="row finance-info">
                <div className="col-xs-12 col-sm-12 col-md-12">
                    <h4>众筹中的项目</h4>

                    <div className="table-responsive">
                        <table className="table">
                            <tr className="active">
                                <th>编号</th>
                                <th>发起人</th>
                                <th>类别</th>
                                <th>目标</th>
                                <th>截止日期</th>
                                <th>标题</th>
                                <th>创建时间</th>
                            </tr>
                            {ksNodes}
                        </table>
                    </div>
                </div>
            </div>
        );
    }
});

var showKickStarters = function (data) {
    React.render(
        <KickStarterBox data={data} />,
        document.getElementById("kickStarters")
    );
};
