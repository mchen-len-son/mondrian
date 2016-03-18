package mondrian.rolap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mondrian.calc.TupleList;
import mondrian.olap.Member;

public class TupleConstraintStruct {
    private List<Member> members;
    private List<TupleList> disjoinedTupleLists;

    public TupleConstraintStruct() {
        members = new ArrayList<Member>();
        disjoinedTupleLists = new ArrayList<TupleList>();
    }

    public TupleConstraintStruct(List<Member> members,
                                 List<TupleList> disjoinedTupleLists) {
        this.members = members;
        this.disjoinedTupleLists = disjoinedTupleLists;
    }

    public TupleConstraintStruct(Member member) {
        members = Collections.singletonList(member);
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void addTupleList(TupleList tupleList) {
        disjoinedTupleLists.add(tupleList);
    }

    public void addAllMembers(List<Member> members) {
        this.members.addAll(members);
    }

    public void addAllDisjointTupleLists(List<TupleList> disjoinedTupleLists) {
        this.disjoinedTupleLists.addAll(disjoinedTupleLists);
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
