package com.zhongyiguolian.zy.ui.home.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;

import com.zhongyiguolian.zy.BR;
import com.zhongyiguolian.zy.R;
import com.zhongyiguolian.zy.base.CustomViewModel;
import com.zhongyiguolian.zy.data.MyRepository;
import com.zhongyiguolian.zy.ui.home.beans.EncyclopediaBeans;

import me.tatarka.bindingcollectionadapter2.ItemBinding;

/**
 * 百科viewmodel
 * @author cdj
 * @date 2020/12/10
 */
public class EncyclopediaViewModel extends CustomViewModel<MyRepository> {

    /**
     * @param application
     * @param model
     */
    public EncyclopediaViewModel(@NonNull Application application, MyRepository model) {
        super(application, model);
    }


    /**
     * 给RecyclerView添加ObservableList
     */
    public ObservableList<EncyclopediaItemViewModel> observableList = new ObservableArrayList<>();


    /**
     * 给RecyclerView添加ItemBinding
     */
    public ItemBinding<EncyclopediaItemViewModel> itemBinding = ItemBinding.of(BR.viewModel, R.layout.item_encyclopedia);

    /**
     * 初始化
     */
    @Override
    public void onCreate() {
        super.onCreate();
        addTestData();
    }

    /**
     * 地方电视台
     */
    public void addTestData(){

        EncyclopediaItemViewModel itemViewModel;

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("什么是Filecoin？","IPFS通过分散的网页自身已经证明了内容寻址的有效性，它提供了全球点对点网络数十亿文件使用。" +
                "它解放了孤岛数据，网络分区存活，离线工作，审查制度路线，产生了持久的数字信息。" +
                "Filecoin是美国协议实验室基于 IPFS 系统开发的激励层区块链项目，一个去中心化存储网络，它让云存储变成一个算法市场。这个市场运行在有着本地协议令牌（也叫做Filecoin）的区块链。其区块链运行在一种叫“时空证明”的新型证明机制上，其区块被存储数据的存储资源贡献者所挖。IPFS是一个网络协议，而Filecoin则是一个基于IPFS的去中心化存储项目。我们可以把Filecoin项目理解为是运行在IPFS网络里的一个激励制度。FIL 是 Filecoin 项目基于 Filecoin 公链发行的 Token，全称是：Filecoin ,符号是：FIL。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("IPFS和Filecoin之间有什么联系?","Filecoin和IPFS是互补协议，两者均由Protocol Labs创建。IPFS 允许网络中的参与者互相存储，索取和传输可验证的数据。 IPFS是开源的，可以被免费下载和使用，并且已经被大量的团队使用。运用IPFS，各个节点可存储它们认为重要的数据；没有简单的方法可以激励他人加入网络或存储特定数据。 为了解决这一关键问题，Filecoin的设计旨在提供一个持久的数据存储系统。在Filecoin的激励结构下，客户付费以在特定的冗余和可用性水平上存储数据，存储资源贡献者通过不断地存储数据并以加密方式证明数据存储来获得付款和奖励。 简而言之：IPFS按内容寻址并使其移动； Filecoin就是缺失的激励机制。" +
                "Filecoin还使用了IPFS的许多性能。例如：" +
                "Filecoin将IPLD用于区块链数据结构" +
                "Filecoin节点使用libp2p保证安全连接" +
                "节点之间的消息传递和Filecoin块传播使用libp2p发布订阅" +
                "此外，Filecoin核心团队包括IPFS核心团队的成员。 IPFS和Filecoin之间的兼容将尽可能无缝对接。即使在Filecoin发布之后，我们仍然期望IPFS和Filecoin的开源社区们继续协作和提升两个项目的兼容性。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("我什么时候应该选择使用Filecoin？","首先，值得重复的是，Filecoin和IPFS相互补充，并且具有显着的交叉兼容性。 我们正努力地使自发的IPFS存储和付费Filecoin存储之间的转换更加简单。" +
                "使用IPFS，您可以通过直接提供硬件或从第三方购买存储来负责您自己的存储节点。 在IPFS上，单独的节点可以存储他们认为重要的内容； 没有任何简单的方法来激励他人来保证储存你的数据在他们的系统里。Filecoin提供了缺少的激励机制。" +
                "如果您希望维护自己的存储节点，或者和外部协作来合作存储数据，IPFS将可能会是您的首选方案。如果您希望支付具有竞争力的价格并在特定的冗余和可用性下为您管理信息存储，Filecoin可能是您的首选方案。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("Filecoin 获取收益的硬件要求是什么？","我们尚未发布Filecoin获取收益的确切硬件规格要求。 存储存储资源贡献者通过为客户存储数据来获取Filecoin代币，并计算加密证明来证明不同时间段的存储。获得的区块奖励和交易收费的概率与存储资源贡献者向Filecoin网络提供的存储容量成正比，而不是Hash算法能力。" +
                "检索存储资源贡献者通过赢取由该文件的市场价值决定的特定文件的投标和获取收益费来获得filecoin。检索云存储服务器的带宽和交易的投标/对交易初始反应时间（即，延迟性和与客户的距离）将决定其在网络上的成交检索交易的能力。检索存储资源贡献者的最大带宽将决定它的交易总量。" +
                "因此，获取收益硬件应该包括用于存储获取收益的充足存储空间，和/或用于检索获取收益的快速互联网连接。我们预计获取收益硬件将包含具有许多硬盘驱动器的配置，可能会类似于网络连接式存储（NAS）设备。CPU和RAM的确切规格尚未公布。我们还注意到，早期存储和检索存储资源贡献者将需要运行Filecoin网络的完整节点。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("我需要ASIC来获取Filecoin吗？","我们正在尽最大努力来设计协议，以便让“专门的Filecoin获取收益硬件”可能是高度优化的硬盘驱动器。因为Filecoin的共识机制基于复制证明（Proof-of-Replication）和时空证明（Proof-of-Spacetime），里面不包含hash密集型的工作量证明（Proof-of-Work)。虽然Filecoin证明确实需要频繁地通过硬件促进存储查找，但把ASICs设计成能支持对大量内存的随机访问可能是昂贵且可能是不切实际的。从实际角度来看，我们希望普通硬盘能够以极具竞争力的速度来生成和验证证明。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("作为存储资源贡献者如何赚取Filecoin?","个体存储资源贡献者可以参与存储和检索市场以获得filecoin。 在存储市场中，存储资源贡献者通过不断证明他们正在存储某一个文件来获取不同时间段的Filecoin。此外，存储市场存储资源贡献者有可能赢得下一个区块，并从区块中包含的交易中赚取区块奖励和费用（类似于其他加密货币的区块奖励）。" +
                "在检索市场中，检索存储资源贡献者通过快速提供客户所寻找的文件来赚取离线支付渠道中的Filecoin。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("为什么说Filecoin是去中心化的？","Filecoin网络之所以是去中心化的是因为任何人，无需任何集中点的许可，都可以：" +
                "通过提供数据存储或检索服务来参与" +
                "使用网络来存储和检索数据" +
                "审核并验证代码库" +
                "通过提出和实施改进意见来完善协议" +
                "无需获得许可即可在网络上构建应用程序，特征或功能" +
                "发出异议并对网络的提出其他选项或方向想法" +
                "分叉代码库" +
                "分叉区块链" +
                "持有或交易filecoin代币" +
                "Filecoin将会是一个任何人都可以贡献或选择运行的开源协议; 此外，这个协议，而不是特定的一家公司，将负责协调参与者之间的互动。 Filecoin 的成功将依赖于其开发者，储存提供者和储存用户社区的各方支持，而不是靠某一家公司的努力。" +
                "希望在此网络上存储文件的客户雇佣存储资源贡献者在网络上存储其文件的多个副本。每一个存储资源贡献者中都必须提交他们不断存储正确数量的数据副本的证明，以获取付款和奖励。可公开验证的证明允许Filecoin协议无需许可地接受任何渴望加入的参与者（无需验证或中心化批准），而仍然提供一个基于设计好的激励机制的担保系统：Filecoin的设计目标是让每个参与者（包括客户，存储资源贡献者，投资者和开发人员）的最获利的选择就是采取行动来提高网络的服务质量。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("什么样的数据会储存在储存节点上？","正如Filecoin白皮书指出，储存存储资源贡献者将存储两种类型的数据：（1）他们同意存储的数据的sealed副本和（2）区块链数据。我们希望存储资源贡献者将充当完整节点来更好地支持协议，和存储和验证整个区块链。由于存储的文件不会包含在区块链中，因此存储整个区块链所需的总存储量将远低于存储资源贡献者为交易存储的sealed数据。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("检索存储资源贡献者如何获取要发送给客户的数据？","我们预计检索存储资源贡献者也将成为存储存储资源贡献者（在这种情况下，他们直接从客户端获取数据），或者如果他们意识到文件需求量很大，他们将从其他存储存储资源贡献者中检索文件。 检索存储资源贡献者不局限于通过Filecoin获取数据。数据也可在IPFS或服务器上临时免费获取。" +
                ""));
        observableList.add(itemViewModel);

        itemViewModel = new EncyclopediaItemViewModel(this,new EncyclopediaBeans("一个基于区块链的协议如何能实现快速检索文件？","虽然存储市场是在链上进行的，但检索市场将完全脱链运营。我们预期为热门数据提供服务的检索存储资源贡献者将遍布全球且高效运作。您可以观看Filecoin存储市场基础知识视频以获取更多信息。" +
                ""));
        observableList.add(itemViewModel);

    }

    /**
     * @param code
     * @param dataBean
     */
    @Override
    protected void returnData(int code, Object dataBean) {
        super.returnData(code, dataBean);

        /*if(code == Constants.GET_FRIEND_MSG_LIST){
            List<MessageFansBean.ListBean> mList = ((MessageFansBean) dataBean).getList();
            //清除旧数据
            if(curPage == FIRST_PAGE){
                observableList.clear();
            }

            if(mList != null && mList.size() > 0){
                for(MessageFansBean.ListBean bean : mList){
                    MessageFansItemViewModel itemViewModel = new MessageFansItemViewModel(this,bean);
                    observableList.add(itemViewModel);
                }
            }

            if(observableList.size() > 0){
                if(mList == null || mList.size() == 0){
                    getUC().getFinishLoadMoreData().call();
                }
            }else{

            }
        }*/
    }
}
