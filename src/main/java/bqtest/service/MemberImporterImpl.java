package bqtest.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MemberImporterImpl implements MemberImporter {


    public Map<String, List<Member>> processFile(File inputMemberFile) throws IOException {

        List<Member> membersFromFile = importMembers(inputMemberFile);

        return splitMembersByState(membersFromFile);

    }


    public List<Member> importMembers(File inputFile) throws IOException {
        return Files.lines(inputFile.toPath())
                .map(line -> {
                   //TODO implement here
                        Member aa= new Member();
                        aa.setId(line.substring(0, 11));
                        aa.setLastName(line.substring(12, 36));
                        aa.setFirstName(line.substring(37, 61));
                        aa.setAddress(line.substring(62, 91));
                        aa.setCity(line.substring(92, 111));
                        aa.setState(line.substring(112, 115));
                        aa.setZip(line.substring(116, 120));
                        return aa;
                }).collect(Collectors.toList());
    }


    public Map<String, List<Member>> splitMembersByState(List<Member> validMembers) {
       //TODO implement this
	   
        List<String> states= new ArrayList<String>();

        Map<String, List<Member>> mapped_by_state= new HashMap<String, List<Member>>();
        for(int i=0;i<validMembers.size();i++){
            Member cc=(Member) validMembers.get(i);
            //System.out.println(cc.getState());
            if (!states.contains(cc.getState())){
                    states.add(cc.getState());
            }	
        } 

        for(int i=0;i<states.size();i++){
            List <String> member_id= new ArrayList<>();

            List <Member> dataset_by_state= new ArrayList<>();
            for(int j=0;j<validMembers.size();j++){
                Member members=(Member) validMembers.get(j);

                if(states.get(i) == null ? members.getState() == null : states.get(i).equals(members.getState())){

                    if(!member_id.contains(members.getId())){
                            dataset_by_state.add(members);
                            member_id.add(members.getId());
                    }
                }
            }

            mapped_by_state.put(states.get(i), dataset_by_state);
        }

        return mapped_by_state;
    }
}
