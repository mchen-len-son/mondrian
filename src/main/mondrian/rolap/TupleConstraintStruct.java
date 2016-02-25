package mondrian.rolap;

import java.util.List;

import mondrian.calc.TupleList;
import mondrian.olap.Member;

public class TupleConstraintStruct {

    List<Member> members;
    List<TupleList> disjoinedTupleLists;

    public TupleConstraintStruct(List<Member> members,
                    List<TupleList> disjoinedTupleLists) {
        this.members = members;
        this.disjoinedTupleLists = disjoinedTupleLists;
    }

    public TupleConstraintStruct() {
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<TupleList> getDisjoinedTupleLists() {
        return disjoinedTupleLists;
    }

    public void setDisjoinedTupleLists(List<TupleList> disjoinedTupleLists) {
        this.disjoinedTupleLists = disjoinedTupleLists;
    }
    
    public Member[] getMembersArray() {
        if(members != null) {
            return members.toArray(new Member[members.size()]);
        } else {
            return new Member[0];
        }
    }

}
