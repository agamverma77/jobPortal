import { Avatar, Indicator } from '@mantine/core';
import { IconAnchor, IconAsset, IconBell, IconSettings } from '@tabler/icons-react';
import NavLinks from './NavLinks';
import { useLocation } from 'react-router-dom';

const Header=()=>{
    return <div className="w-full bg-mine-shaft-950 px-6 text-white h-20 flex justify-between items-center">
        <div className='flex gap-1 items-center text-bright-sun-400'>
            <IconAnchor className='h-8 w-8' stroke={2.5}/>
            <div className='text-3xl f4ont-semibold'>JobHook</div>
        </div>
        {NavLinks()}
        <div className='flex gap-3 items-center'>
            
            <div className='flex items-center gap-3'>
                <div>Marshal</div>
                <Avatar src="avatar-9.png" alt="it's me" />
            </div>
            <div className='bg-mine-shaft-900 p-1.5 rounded-full'>
                <IconSettings stroke={1.5}/>
            </div>
            <div className='bg-mine-shaft-900 p-1.5 rounded-full'>
                <Indicator color="bright-sun.4" offset={6} size={8} processing>
                    <IconBell/>
                </Indicator>
            </div>
        </div>
    </div> 
    }
export default Header;