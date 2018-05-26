<?php
namespace system;


/**
 * Class DFFI
 * @package system
 */
class DFFI
{
	/**
	 * DFFI constructor.
     * @param string $lib
     */
    public function __construct($lib){ }
	
	/**
     * Bind function
     * 
     * @param string $functionName
     * @param string $returnType
     * @param array $types
     */
	public function bind($functionName, $returnType, $types) {}
}