import { Avatar, Indicator } from '@mantine/core';
import { IconAnchor, IconAsset, IconBell, IconSettings } from '@tabler/icons-react';

const Header=()=>{
    return <div className="w-full bg-mine-shaft-950 px-6 text-white h-20 flex justify-between items-center ">
        <div className='flex gap-1 items-center text-bright-sun-400'>
            <IconAnchor className='h-10 w-8' stroke={2.5}/>
            <div className='text-3xl font-semibold'>JobHook</div>
        </div>
        <div className='flex gap-5'>
            <a href="">Find Jobs</a>
            <a href="">Find Talent</a>
            <a href="">Upload Job</a>
            <a href="">About Us</a>
        </div>
        <div className='flex gap-3 items-center'>
            
            <div className='flex items-center gap-3'>
                <div>Marshal</div>
                <Avatar src="avatar-9.png" alt="it's me" />
            </div>
            <div className='bg-mine-shaft-900 p-1.5 rounded-full'>
                <IconSettings stroke={1.5}/>
            </div>
            <div className='bg-mine-shaft-900 p-1.5 rounded-full'>
                <Indicator color="pink" offset={6} size={8} processing>
                    <IconBell/>
                </Indicator>
            </div>
        </div>
    </div>
}
export default Header;