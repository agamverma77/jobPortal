import { Divider } from "@mantine/core";
<<<<<<< HEAD
import SearchBar from "../FindJobs/SearchJobs";
=======

import Talents from "../FindTalent/Talents";
import SearchBar from "../FindTalent/SearchBar";
>>>>>>> master

const FindTalentPage=()=>{
    return <div className="min-h-[90vh] bg-mine-shaft-950 font-['poppins']">
         <Divider size="xs" mx="md"/>
            <SearchBar/>
            <Divider size="xs" mx="md"/>
            <Talents/>
    </div>
}
export default FindTalentPage;