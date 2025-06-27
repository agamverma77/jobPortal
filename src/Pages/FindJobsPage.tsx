import { Divider } from "@mantine/core";
import Jobs from "../FindJobs/Jobs";
import SearchBar from "../FindJobs/SearchJobs";


const FindJobsPage = () => {
    return (
        <div className="min-h-[90vh] bg-mine-shaft-950 font-['poppins']">
            <Divider size="xs" mx="md"/>
            <SearchBar/>
            <Divider size="xs" mx="md"/>
            <Jobs/>
        </div>
    )
}
export default FindJobsPage;